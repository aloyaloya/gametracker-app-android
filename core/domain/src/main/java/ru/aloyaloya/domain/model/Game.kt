package ru.aloyaloya.domain.model

// TODO: MockRealization

data class Game(
    val id: Int,
    val title: String,
    val tags: List<String>,
    val imageUrl: String,
    val status: GameStatus
)