package ru.aloyaloya.discover.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.aloyaloya.design_system.component.DraggableSheet
import ru.aloyaloya.design_system.component.FeatureCardCarousel
import ru.aloyaloya.design_system.component.HorizontalGridPager
import ru.aloyaloya.design_system.component.PrimaryButton
import ru.aloyaloya.design_system.component.SectionContainer
import ru.aloyaloya.design_system.component.card.GameStandardCard
import ru.aloyaloya.design_system.component.card.GenreCard
import ru.aloyaloya.design_system.component.navigation.TopAppBar
import ru.aloyaloya.discover.R
import ru.aloyaloya.discover.presentation.model.DiscoverEvent
import ru.aloyaloya.discover.presentation.model.DiscoverUiState
import ru.aloyaloya.domain.model.Game
import ru.aloyaloya.domain.model.Genre
import ru.aloyaloya.ui.model.DraggableSheetState
import ru.aloyaloya.ui.model.TopBarAction
import ru.aloyaloya.ui.utils.rememberDraggableSheetState

@Composable
internal fun DiscoverRoute(
    uiState: DiscoverUiState,
    onEvent: (DiscoverEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    DiscoverScreen(
        uiState = uiState,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
private fun DiscoverScreen(
    uiState: DiscoverUiState,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberDraggableSheetState(headerHeight = 360.dp)
    val progress = sheetState.progress

    val bottomColor by animateColorAsState(
        targetValue = lerp(
            MaterialTheme.colorScheme.surfaceContainerHigh,
            MaterialTheme.colorScheme.onPrimaryContainer,
            1f - progress
        ),
        label = "BottomGradientColor"
    )

    Column(
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    0f to MaterialTheme.colorScheme.surface,
                    1f to bottomColor
                )
            )
            .statusBarsPadding()
    ) {
        TopAppBar(
            title = stringResource(R.string.discover_screen_title),
            rightAction = TopBarAction(
                iconResId = R.drawable.ic_search_outline,
                descriptionResId = R.string.discover_screen_title,
                actionUnit = {}
            )
        )

        when (uiState) {
            DiscoverUiState.Loading -> {
                CircularProgressIndicator()
            }

            is DiscoverUiState.Success -> {
                DiscoverContent(
                    content = uiState,
                    sheetState = sheetState,
                    onGenreClick = {},
                    onGameClick = {}
                )
            }

            DiscoverUiState.Error -> {
                Text("Пусто")
            }
        }
    }
}

@Composable
private fun DiscoverContent(
    content: DiscoverUiState.Success,
    sheetState: DraggableSheetState,
    onGenreClick: (Int) -> Unit, // TODO: Change to real genre ID
    onGameClick: (Int) -> Unit, // TODO: Change to real game ID
    modifier: Modifier = Modifier
) {
    DraggableSheet(
        modifier = modifier,
        state = sheetState,
        headerContent = {
            Column(
                modifier = Modifier.padding(bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DiscoverFeatureSection(
                    features = content.featureGames,
                    onShowMore = { /* TODO */ },
                    onGameClick = onGameClick,
                    modifier = Modifier.weight(1f)
                )
                PrimaryButton(
                    text = "Помочь с выбором",
                    onClick = {},
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                )
            }
        },
        sheetContent = {
            content.gamesFeed.take(2).forEach { section ->
                item(key = section::class.simpleName) {
                    DiscoverFeedSection(
                        feed = stringResource(section.titleRes),
                        games = section.games,
                        onShowMore = { /* TODO */ },
                        onGameClick = onGameClick
                    )
                }
            }

            item {
                DiscoverGenreSection(
                    genres = content.genres,
                    onShowMore = { /* TODO */ },
                    onGenreClick = onGenreClick
                )
            }

            content.gamesFeed.subList(2, content.gamesFeed.size).forEach { section ->
                item(key = section::class.simpleName) {
                    DiscoverFeedSection(
                        feed = stringResource(section.titleRes),
                        games = section.games,
                        onShowMore = { /* TODO */ },
                        onGameClick = onGameClick
                    )
                }
            }
        }
    )
}

@Composable
private fun DiscoverFeatureSection(
    features: List<Game>,
    onShowMore: () -> Unit,
    onGameClick: (Int) -> Unit, // TODO: Change to real ID
    modifier: Modifier = Modifier
) {
    SectionContainer(
        title = stringResource(R.string.section_features),
        onShowMore = onShowMore,
        modifier = modifier
    ) {
        FeatureCardCarousel(
            featureGames = features,
            onClick = onGameClick,
        )
    }
}

@Composable
private fun DiscoverGenreSection(
    genres: List<Genre>,
    onShowMore: () -> Unit,
    onGenreClick: (Int) -> Unit, // TODO: Change to real ID
    modifier: Modifier = Modifier,
    cardsPerPage: Int = 2
) {
    SectionContainer(
        title = stringResource(R.string.section_genres),
        onShowMore = onShowMore,
        modifier = modifier
    ) {
        HorizontalGridPager(
            items = genres,
            itemsPerPage = cardsPerPage
        ) { genre, cardModifier ->
            GenreCard(
                title = genre.title,
                onClick = { onGenreClick(genre.id) },
                modifier = cardModifier
            )
        }
    }
}

@Composable
private fun DiscoverFeedSection(
    feed: String,
    games: List<Game>,
    onShowMore: () -> Unit,
    onGameClick: (Int) -> Unit, // TODO: Change to real ID
    modifier: Modifier = Modifier,
    cardsPerPage: Int = 3
) {
    SectionContainer(
        title = feed,
        onShowMore = onShowMore,
        modifier = modifier
    ) {
        HorizontalGridPager(
            items = games,
            itemsPerPage = cardsPerPage
        ) { game, cardModifier ->
            GameStandardCard(
                title = game.title,
                status = game.status,
                imageUrl = game.imageUrl,
                onClick = { onGameClick(game.id) },
                modifier = cardModifier
            )
        }
    }
}

