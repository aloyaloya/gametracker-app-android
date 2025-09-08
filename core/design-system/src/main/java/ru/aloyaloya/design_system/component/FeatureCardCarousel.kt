package ru.aloyaloya.design_system.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import ru.aloyaloya.design_system.component.card.GameFeatureCard
import ru.aloyaloya.domain.model.Game
import kotlin.math.absoluteValue

/**
 * A horizontally scrollable carousel displaying featured game cards.
 *
 * Each card scales smoothly depending on its position in the pager,
 * highlighting the currently focused card in the center.
 *
 * @param featureGames List of [Game] items to display in the carousel.
 * @param onClick Callback invoked when a card is clicked, providing the game's ID.
 * @param modifier [Modifier] to be applied to the carousel layout.
 */
@Composable
fun FeatureCardCarousel(
    featureGames: List<Game>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(
        initialPage = featureGames.size / 2,
        pageCount = { featureGames.size }
    )

    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = FeatureCardCarouselDefaults.CAROUSEL_CONTENT_PADDING.dp
        ),
        pageSpacing = FeatureCardCarouselDefaults.CAROUSEL_PAGE_SPACING.dp
    ) { page ->

        val game = featureGames[page]
        val pageOffset = (page - pagerState.currentPage) - pagerState.currentPageOffsetFraction

        val scale = lerp(
            start = 0.9f,
            stop = 1f,
            fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
        )

        GameFeatureCard(
            title = game.title,
            imageUrl = game.imageUrl,
            status = game.status,
            tags = game.tags,
            onClick = { onClick(game.id) },
            modifier = Modifier.graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
        )
    }
}

/**
 * Default styling values for [FeatureCardCarousel].
 */
private object FeatureCardCarouselDefaults {
    const val CAROUSEL_CONTENT_PADDING = 24
    const val CAROUSEL_PAGE_SPACING = 0
}