package ru.aloyaloya.design_system.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.aloyaloya.design_system.component.AsyncDynamicImage
import ru.aloyaloya.design_system.component.card.FeatureCardDefaults.DEFAULT_CARD_HEIGHT
import ru.aloyaloya.design_system.component.card.FeatureCardDefaults.DEFAULT_CARD_SHAPE
import ru.aloyaloya.design_system.component.card.FeatureCardDefaults.DEFAULT_CHIP_PADDING
import ru.aloyaloya.design_system.component.card.FeatureCardDefaults.DEFAULT_CONTENT_HORIZONTAL_PADDING
import ru.aloyaloya.design_system.component.card.FeatureCardDefaults.DEFAULT_CONTENT_ITEMS_SPACER
import ru.aloyaloya.design_system.component.card.FeatureCardDefaults.DEFAULT_CONTENT_VERTICAL_PADDING
import ru.aloyaloya.design_system.component.card.FeatureCardDefaults.DEFAULT_TAGS_ITEMS_SPACER
import ru.aloyaloya.design_system.component.card.FeatureCardDefaults.cardShadowGradient
import ru.aloyaloya.design_system.extension.dropShadow
import ru.aloyaloya.domain.model.GameStatus

// TODO: Add platform chip implementations

/**
 * A featured game card component designed for prominent display of game information.
 *
 * This card showcases a game with a large cover image, title, status indicator
 * in a large format,and associated tags in an elegant layout.
 * It features a gradient overlay for optimal text readability and is
 * designed to be the focal point in a collection or featured section.
 *
 * Unlike the standard card, this version emphasizes visual appeal and displays additional
 * metadata (tags) below the title in a horizontal list.
 *
 * @param title The title of the game to display prominently at the bottom of the card.
 * @param imageUrl The URL of the game's cover image to be loaded asynchronously.
 * @param status The [GameStatus] of the game, displayed as a chip in the top-right corner.
 * @param tags A list of tags associated with the game (e.g., genres, platforms, features).
 * @param onClick The callback to be invoked when the card is clicked.
 * @param modifier The [Modifier] to be applied to this card layout.
 */
@Composable
fun GameFeatureCard(
    title: String,
    imageUrl: String,
    status: GameStatus,
    tags: List<String>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .dropShadow(shape = RoundedCornerShape(DEFAULT_CARD_SHAPE.dp))
            .clip(shape = RoundedCornerShape(DEFAULT_CARD_SHAPE.dp))
            .clickable { onClick() }
            .height(DEFAULT_CARD_HEIGHT.dp)
    ) {
        AsyncDynamicImage(imageUrl = imageUrl)
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(cardShadowGradient)
        )
        StatusChip(
            status = status,
            textVisible = true,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(DEFAULT_CHIP_PADDING.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(DEFAULT_CONTENT_ITEMS_SPACER.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(
                    horizontal = DEFAULT_CONTENT_HORIZONTAL_PADDING.dp,
                    vertical = DEFAULT_CONTENT_VERTICAL_PADDING.dp
                )
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            Row(horizontalArrangement = Arrangement.spacedBy(DEFAULT_TAGS_ITEMS_SPACER.dp)) {
                tags.forEach { tag ->
                    Text(
                        text = tag,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.surfaceBright
                    )
                }
            }
        }
    }
}

/**
 * Default values for [GameFeatureCard] styling, dimensions, and visual properties.
 *
 * Contains constants for card size, shape, padding, spacing, and the gradient overlay
 * that ensures text readability on various background images. The values are optimized
 * for a larger, more prominent card layout compared to the standard version.
 */
private object FeatureCardDefaults {
    const val DEFAULT_CARD_HEIGHT = 240
    const val DEFAULT_CARD_SHAPE = 16
    const val DEFAULT_CONTENT_HORIZONTAL_PADDING = 16
    const val DEFAULT_CONTENT_VERTICAL_PADDING = 24
    const val DEFAULT_CONTENT_ITEMS_SPACER = 4
    const val DEFAULT_CHIP_PADDING = 16
    const val DEFAULT_TAGS_ITEMS_SPACER = 8

    /**
     * Vertical gradient overlay applied to the card image to improve text contrast.
     * Provides shadow at the top and bottom while keeping the middle section transparent
     * to maintain image visibility.
     */
    val cardShadowGradient = Brush.verticalGradient(
        0f to Color.Black.copy(alpha = 0.4f),
        0.2f to Color.Transparent,
        0.6f to Color.Transparent,
        1f to Color.Black.copy(alpha = 0.8f)
    )
}
