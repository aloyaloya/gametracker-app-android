package ru.aloyaloya.discover.presentation.model

import androidx.annotation.StringRes
import ru.aloyaloya.discover.R
import ru.aloyaloya.domain.model.Game

sealed class GamesFeed(
    @StringRes val titleRes: Int,
    val games: List<Game>
) {
    class New(games: List<Game>) : GamesFeed(R.string.section_new, games)
    class ForYou(games: List<Game>) : GamesFeed(R.string.section_for_you, games)
    class ComingSoon(games: List<Game>) : GamesFeed(R.string.section_coming_soon, games)
    class SimilarTo(
        games: List<Game>,
        val targetGame: String
    ) : GamesFeed(R.string.section_similar_to, games)
}