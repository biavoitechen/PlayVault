// com.playvault.util/UiMappers.kt
package com.playvault.util

// Assumimos que estes Domain Models são definidos pela Pessoa 4
import com.playvault.domain.model.FriendDomain
import com.playvault.domain.model.GameDomain
import com.playvault.domain.model.LibraryEntryDomain

// Assumimos que estes UI Contracts são definidos pela Pessoa 1
import com.playvault.contracts.FriendUi
import com.playvault.contracts.GameUi
import com.playvault.contracts.LibraryGameUi
import java.util.concurrent.TimeUnit

object UiMapper {

    /** Mapeia GameDomain (Catálogo) para GameUi (Loja) */
    fun GameDomain.toGameUi(): GameUi {
        val formattedPrice = if (price == 0.0) "Grátis" else "R$ %.2f".format(price)

        return GameUi(
            id = id,
            title = title,
            genres = genres,
            price = formattedPrice,
            imageUrl = coverUrl,
            releaseDate = releaseDate,
            popularityScore = popularityScore
        )
    }

    /** Mapeia LibraryEntryDomain para LibraryGameUi (Biblioteca) */
    fun LibraryEntryDomain.toLibraryGameUi(): LibraryGameUi {
        val hours = TimeUnit.MINUTES.toHours(totalPlaytimeMinutes)
        val minutes = totalPlaytimeMinutes % 60
        val formattedTime = "${hours}h ${minutes}m"

        return LibraryGameUi(
            id = gameId,
            title = gameTitle,
            coverUrl = gameCoverUrl,
            hoursPlayed = formattedTime,
            isInstalled = isInstalled
        )
    }

    /** Mapeia FriendDomain para FriendUi (Lista de Amigos) */
    fun FriendDomain.toFriendUi(): FriendUi {
        val statusText = if (isPlayingGame != null) "Jogando $isPlayingGame" else if (isOnline) "Online" else "Offline"

        return FriendUi(
            id = id,
            username = username,
            status = statusText,
            avatarUrl = avatarUrl
        )
    }
}