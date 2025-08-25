package ru.aloyaloya.domain.model

import ru.aloyaloya.domain.R

enum class GameStatus(
    val labelResId: Int,
    val iconResId: Int
) {
    COMPLETED(
        labelResId = R.string.status_completed,
        iconResId = R.drawable.ic_completed_status
    ),
    PLAYING(
        labelResId = R.string.status_playing,
        iconResId = R.drawable.ic_playing_status
    ),
    BACKLOG(
        labelResId = R.string.status_backlog,
        iconResId = R.drawable.ic_backlog_status
    ),
    WISHLIST(
        labelResId = R.string.status_wishlist,
        iconResId = R.drawable.ic_wishlist_status
    )
}