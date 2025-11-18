package com.playvault.util

import com.playvault.domain.FriendDomain
import com.playvault.domain.GameDomain
import com.playvault.domain.LibraryEntryDomain
import com.playvault.contracts.FriendUi
import com.playvault.contracts.GameUi
import com.playvault.contracts.LibraryGameUi
import java.util.concurrent.TimeUnit

object UiMapper {

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

    fun FriendDomain.toFriendUi(): FriendUi {
        val statusText =
            if (isPlayingGame != null) "Jogando $isPlayingGame"
            else if (isOnline) "Online" else "Offline"

        return FriendUi(
            id = id,
            username = username,
            status = statusText,
            avatarUrl = avatarUrl
        )
    }
}
