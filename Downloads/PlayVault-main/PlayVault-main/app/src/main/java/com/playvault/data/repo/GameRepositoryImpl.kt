package com.playvault.data.repo

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.playvault.data.dao.GameDao
import com.playvault.data.entity.GameEntity
import kotlinx.coroutines.tasks.await

/**
 * Repositório que une Firestore (remoto) + Room (local)
 * e fornece os dados para os ViewModels.
 */
class GameRepositoryImpl(
    private val gameDao: GameDao,
    private val firestore: FirebaseFirestore
) {

    private val collectionName = "games" // Nome da coleção no Firestore

    // -------------------------------
    // 🔹 Função para obter jogos do Firestore e salvar localmente
    // -------------------------------
    suspend fun syncGames() {
        try {
            val snapshot = firestore.collection(collectionName).get().await()
            val games = snapshot.documents.mapNotNull { doc ->
                val title = doc.getString("title") ?: return@mapNotNull null
                val description = doc.getString("description") ?: ""
                val imageUrl = doc.getString("imageUrl") ?: ""
                val isFeatured = doc.getBoolean("isFeatured") ?: false

                GameEntity(
                    id = 0, // O Room gera ID automaticamente
                    name = title,
                    description = description,
                    imageUrl = imageUrl,
                    isFeatured = isFeatured
                )
            }

            // Atualiza banco local
            gameDao.deleteAll()
            gameDao.insertAll(games)

            Log.d("GameRepository", "Sincronização concluída: ${games.size} jogos salvos no Room")

        } catch (e: Exception) {
            Log.e("GameRepository", "Erro ao sincronizar jogos: ${e.message}")
        }
    }

    // -------------------------------
    // 🔹 Função para listar jogos locais
    // -------------------------------
    suspend fun getLocalGames(): List<GameEntity> {
        return try {
            gameDao.getAllGames()
        } catch (e: Exception) {
            Log.e("GameRepository", "Erro ao buscar jogos locais: ${e.message}")
            emptyList()
        }
    }

    // -------------------------------
    // 🔹 CRUD remoto (Admin)
    // -------------------------------

    suspend fun addGame(game: GameEntity) {
        try {
            val data = hashMapOf(
                "title" to game.name,
                "description" to game.description,
                "imageUrl" to game.imageUrl,
                "isFeatured" to game.isFeatured
            )
            firestore.collection(collectionName).add(data).await()
        } catch (e: Exception) {
            Log.e("GameRepository", "Erro ao adicionar jogo: ${e.message}")
        }
    }

    suspend fun updateGame(docId: String, game: GameEntity) {
        try {
            val data = mapOf(
                "title" to game.name,
                "description" to game.description,
                "imageUrl" to game.imageUrl,
                "isFeatured" to game.isFeatured
            )
            firestore.collection(collectionName).document(docId).set(data).await()
        } catch (e: Exception) {
            Log.e("GameRepository", "Erro ao atualizar jogo: ${e.message}")
        }
    }

    suspend fun deleteGame(docId: String) {
        try {
            firestore.collection(collectionName).document(docId).delete().await()
        } catch (e: Exception) {
            Log.e("GameRepository", "Erro ao deletar jogo: ${e.message}")
        }
    }
}
