package ru.aloyaloya.design_system.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.aloyaloya.design_system.component.AsyncDynamicImage
import ru.aloyaloya.design_system.component.card.StandardCardDefaults.DEFAULT_CARD_HEIGHT
import ru.aloyaloya.design_system.component.card.StandardCardDefaults.DEFAULT_CARD_SHAPE
import ru.aloyaloya.design_system.component.card.StandardCardDefaults.DEFAULT_CARD_WIDTH
import ru.aloyaloya.design_system.component.card.StandardCardDefaults.DEFAULT_CHIP_PADDING
import ru.aloyaloya.design_system.component.card.StandardCardDefaults.DEFAULT_TITLE_END_PADDING
import ru.aloyaloya.design_system.component.card.StandardCardDefaults.DEFAULT_TITLE_START_PADDING
import ru.aloyaloya.design_system.component.card.StandardCardDefaults.DEFAULT_TITLE_VERTICAL_PADDING
import ru.aloyaloya.design_system.component.card.StandardCardDefaults.cardShadowGradient
import ru.aloyaloya.design_system.extension.dropShadow
import ru.aloyaloya.domain.model.GameStatus

/**
 * A standard card component for displaying game information in a consistent layout.
 *
 * This card displays a game's cover image, title, and status indicator in a compact format.
 * It features a gradient overlay for better text readability and a visual status chip.
 * The card is clickable and has a consistent size suitable for grid layouts.
 *
 * @param title The title of the game to display at the bottom of the card.
 * @param status The [GameStatus] of the game, displayed as a chip in the top-right corner.
 * @param imageUrl The URL of the game's cover image to be loaded asynchronously.
 * @param onClick The callback to be invoked when the card is clicked.
 * @param modifier The [Modifier] to be applied to this card layout.
 */
@Composable
fun GameStandardCard(
    title: String,
    status: GameStatus,
    imageUrl: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .dropShadow(shape = RoundedCornerShape(DEFAULT_CARD_SHAPE.dp))
            .clip(shape = RoundedCornerShape(DEFAULT_CARD_SHAPE.dp))
            .clickable { onClick() }
            .height(DEFAULT_CARD_HEIGHT.dp)
            .width(DEFAULT_CARD_WIDTH.dp)
    ) {
        AsyncDynamicImage(imageUrl = imageUrl)
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(cardShadowGradient)
        )
        StatusChip(
            status = status,
            textVisible = false,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(DEFAULT_CHIP_PADDING.dp)
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(vertical = DEFAULT_TITLE_VERTICAL_PADDING.dp)
                .padding(
                    start = DEFAULT_TITLE_START_PADDING.dp,
                    end = DEFAULT_TITLE_END_PADDING.dp
                ),
            text = title,
            minLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

/**
 * Default values for [GameStandardCard] styling, dimensions, and visual properties.
 *
 * Contains constants for card size, shape, padding, and the gradient overlay
 * that ensures text readability on various background images.
 */
private object StandardCardDefaults {
    const val DEFAULT_CARD_HEIGHT = 160
    const val DEFAULT_CARD_WIDTH = 120
    const val DEFAULT_CARD_SHAPE = 16
    const val DEFAULT_TITLE_VERTICAL_PADDING = 8
    const val DEFAULT_TITLE_START_PADDING = 8
    const val DEFAULT_TITLE_END_PADDING = 16
    const val DEFAULT_CHIP_PADDING = 8

    /**
     * Vertical gradient overlay applied to the card image to improve text contrast.
     * Provides shadow at the top and bottom while keeping the middle section transparent
     * to maintain image visibility.
     */
    val cardShadowGradient = Brush.verticalGradient(
        0f to Color.Black.copy(alpha = 0.4f),
        0.2f to Color.Transparent,
        0.4f to Color.Transparent,
        1f to Color.Black.copy(alpha = 0.8f)
    )
}