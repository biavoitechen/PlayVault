package com.playvault.data.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreService {

    private val db = FirebaseFirestore.getInstance()
    private val gamesCollection = db.collection("games")

    suspend fun getAllGames(): List<GameRemote> {
        return gamesCollection.get().await().documents.map { doc ->
            doc.toObject(GameRemote::class.java)!!.copy(id = doc.id)
        }
    }

    suspend fun createGame(game: GameRemote) {
        gamesCollection.add(game).await()
    }

    suspend fun updateGame(game: GameRemote) {
        gamesCollection.document(game.id).set(game).await()
    }

    suspend fun deleteGame(id: String) {
        gamesCollection.document(id).delete().await()
    }

    suspend fun setFeatured(id: String, isFeatured: Boolean) {
        gamesCollection.document(id).update("isFeatured", isFeatured).await()
    }
}
