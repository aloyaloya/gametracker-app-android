package ru.aloyaloya.design_system.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.aloyaloya.design_system.extension.dropShadow

// TODO: Write KDoc

@Composable
fun GenreCard(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .dropShadow(shape = RoundedCornerShape(GenreCardDefaults.CARD_SHAPE.dp))
            .clickable { onClick() }
            .background(
                shape = RoundedCornerShape(GenreCardDefaults.CARD_SHAPE.dp),
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.inversePrimary
                    )
                )
            )
            .padding(all = GenreCardDefaults.CARD_PADDING.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            maxLines = 2,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

private object GenreCardDefaults {
    const val CARD_SHAPE = 16
    const val CARD_PADDING = 16

    /**
     * Vertical gradient overlay applied to the card image to improve text contrast.
     * Provides shadow at the top and bottom while keeping the middle section transparent
     * to maintain image visibility.
     */
    val cardShadowGradient = Brush.verticalGradient(
        0f to Color.Black.copy(alpha = 0.6f),
        0.2f to Color.Transparent,
        0.4f to Color.Transparent,
        1f to Color.Black.copy(alpha = 0.7f)
    )
}