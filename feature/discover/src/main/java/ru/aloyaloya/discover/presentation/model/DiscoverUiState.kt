package ru.aloyaloya.discover.presentation.model

import ru.aloyaloya.domain.model.Game
import ru.aloyaloya.domain.model.Genre

sealed interface DiscoverUiState {
    data object Loading : DiscoverUiState

    data class Success(
        val genres: List<Genre>,
        val featureGames: List<Game>,
        val gamesFeed: List<GamesFeed>
    ) : DiscoverUiState

    data object Error : DiscoverUiState
}