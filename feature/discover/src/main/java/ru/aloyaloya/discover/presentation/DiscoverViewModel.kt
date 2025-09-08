package ru.aloyaloya.discover.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import ru.aloyaloya.discover.presentation.model.DiscoverUiState
import ru.aloyaloya.discover.presentation.model.GamesFeed
import ru.aloyaloya.domain.model.Game
import ru.aloyaloya.domain.model.GameStatus
import ru.aloyaloya.domain.model.Genre
import javax.inject.Inject

class DiscoverViewModel @Inject constructor() : ViewModel() {

    val uiState: StateFlow<DiscoverUiState> =
        combine(
            getGenres(),
            getFeatureGames(),
            getGamesFeed(),
        ) { genres, featureGames, gamesFeed ->
            DiscoverUiState.Success(
                genres = genres,
                featureGames = featureGames,
                gamesFeed = gamesFeed
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DiscoverUiState.Loading,
            )

    private fun getGenres(): Flow<List<Genre>> = flow {
        delay(500)
        emit(generateMockGenres())
    }

    private fun getFeatureGames(): Flow<List<Game>> = flow {
        delay(500)
        emit(generateMockFeatureGames())
    }

    private fun getGamesFeed(): Flow<List<GamesFeed>> = flow {
        delay(1000) // имитация задержки
        emit(generateMockGamesFeed())
    }

    private fun generateMockFeatureGames(): List<Game> {
        return List(5) { index ->
            Game(
                id = index + 1,
                title = "The Witcher 3: Wild Hunt",
                tags = listOf("RPG", "Action-RPG", "Story"),
                imageUrl = "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/292030/ad9240e088f953a84aee814034c50a6a92bf4516/header.jpg?t=1749199563",
                status = GameStatus.PLAYING
            )
        }
    }

    private fun generateMockGenres(): List<Genre> {
        return List(5) { index ->
            Genre(
                id = index + 201,
                title = "Action",
                imageUrl = "https://static.wikia.nocookie.net/borderlands/images/6/65/Boderlands_2_-_Boxart.jpg/revision/latest/thumbnail/width/360/height/360?cb=20121102181148&path-prefix=ru"
            )
        }
    }

    private fun generateMockGamesFeed(): List<GamesFeed> {
        val newGames = List(9) { index ->
            Game(
                id = index + 1,
                title = "The Witcher 3: Wild Hunt",
                tags = listOf("RPG", "Action-RPG", "Story"),
                imageUrl = "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/292030/ad9240e088f953a84aee814034c50a6a92bf4516/header.jpg?t=1749199563",
                status = GameStatus.PLAYING
            )
        }

        val forYouGames = List(9) { index ->
            Game(
                id = index + 101,
                title = "The Witcher 3: Wild Hunt",
                tags = listOf("RPG", "Action-RPG", "Story"),
                imageUrl = "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/292030/ad9240e088f953a84aee814034c50a6a92bf4516/header.jpg?t=1749199563",
                status = GameStatus.PLAYING
            )
        }

        val comingSoonGames = List(9) { index ->
            Game(
                id = index + 301,
                title = "The Witcher 3: Wild Hunt",
                tags = listOf("RPG", "Action-RPG", "Story"),
                imageUrl = "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/292030/ad9240e088f953a84aee814034c50a6a92bf4516/header.jpg?t=1749199563",
                status = GameStatus.COMPLETED
            )
        }

        val similarGames = List(9) { index ->
            Game(
                id = index + 401,
                title = "The Witcher 3: Wild Hunt",
                tags = listOf("RPG", "Action-RPG", "Story"),
                imageUrl = "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/292030/ad9240e088f953a84aee814034c50a6a92bf4516/header.jpg?t=1749199563",
                status = GameStatus.COMPLETED
            )
        }

        return listOf(
            GamesFeed.New(newGames),
            GamesFeed.ForYou(forYouGames),
            GamesFeed.ComingSoon(comingSoonGames),
            GamesFeed.SimilarTo(
                games = similarGames,
                targetGame = "Baldurs Gate 3"
            )
        )
    }
}

private fun GamesFeed.getFormattedTitle(context: Context): String {
    return when (this) {
        is GamesFeed.SimilarTo -> context.getString(titleRes, targetGame)
        else -> context.getString(titleRes)
    }
}