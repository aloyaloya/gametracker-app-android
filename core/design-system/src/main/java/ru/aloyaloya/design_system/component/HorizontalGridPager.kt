package ru.aloyaloya.design_system.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A horizontal pager that displays multiple items per page, laid out in a row.
 *
 * This composable is useful for creating a horizontally scrolling grid-like
 * experience where each "page" contains a fixed number of items.
 *
 * @param items The list of items to display in the pager.
 * @param itemsPerPage The number of items to show on each page.
 * @param modifier Optional [Modifier] to apply to the pager container.
 * @param pageSpacing The spacing between pages.
 * @param cardSpacing The spacing between items inside a page.
 * @param contentPadding Padding applied to the start and end of the pager.
 * @param itemContent A composable that renders a single item, given the item and a [Modifier].
 */
@Composable
fun <T> HorizontalGridPager(
    items: List<T>,
    itemsPerPage: Int,
    modifier: Modifier = Modifier,
    pageSpacing: Dp = HorizontalGridPagerDefaults.DEFAULT_PAGE_SPACING.dp,
    cardSpacing: Dp = HorizontalGridPagerDefaults.DEFAULT_CARD_SPACING.dp,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = HorizontalGridPagerDefaults.DEFAULT_HORIZONTAL_CONTENT_PADDING.dp
    ),
    itemContent: @Composable (item: T, modifier: Modifier) -> Unit
) {
    val pagerState = rememberPagerState {
        // Calculate number of pages, rounding up
        (items.size + itemsPerPage - 1) / itemsPerPage
    }

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        pageSpacing = pageSpacing,
        contentPadding = contentPadding
    ) { pageIndex ->
        BoxWithConstraints {
            val cardWidth = calculateCardWidth(
                maxWidth = maxWidth,
                cardsPerRow = itemsPerPage.toFloat(),
                cardSpacing = cardSpacing
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(cardSpacing)
            ) {
                val start = pageIndex * itemsPerPage
                val end = minOf(start + itemsPerPage, items.size)

                items.subList(start, end).forEach { item ->
                    itemContent(item, Modifier.width(cardWidth))
                }
            }
        }
    }
}

/**
 * Default values for [HorizontalGridPager].
 */
private object HorizontalGridPagerDefaults {
    const val DEFAULT_PAGE_SPACING = 16
    const val DEFAULT_CARD_SPACING = 16
    const val DEFAULT_HORIZONTAL_CONTENT_PADDING = 24
}

/**
 * Calculates the width of each card given the available max width, number of cards per row, and spacing.
 *
 * @param maxWidth The maximum available width.
 * @param cardsPerRow Number of cards to fit in a row.
 * @param cardSpacing Spacing between cards.
 * @return The calculated width for each card.
 */
private fun calculateCardWidth(
    maxWidth: Dp,
    cardsPerRow: Float,
    cardSpacing: Dp
): Dp {
    return (maxWidth - cardSpacing * (cardsPerRow - 1)) / cardsPerRow
}