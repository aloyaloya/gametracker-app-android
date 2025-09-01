package ru.aloyaloya.design_system.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.aloyaloya.design_system.component.AsyncDynamicImage
import ru.aloyaloya.design_system.component.card.GenreCardDefaults.cardShadowGradient
import ru.aloyaloya.design_system.extension.dropShadow

// TODO: Write KDoc

@Composable
fun GenreCard(
    title: String,
    imageUrl: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .dropShadow(shape = RoundedCornerShape(GenreCardDefaults.CARD_SHAPE.dp))
            .clickable { onClick() }
            .clip(RoundedCornerShape(GenreCardDefaults.CARD_SHAPE.dp))
            .aspectRatio(GenreCardDefaults.CARD_ASPECT_RATIO)
    ) {
        AsyncDynamicImage(imageUrl = imageUrl)

        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(GenreCardDefaults.CARD_SHAPE.dp))
                .background(cardShadowGradient)
        )

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            maxLines = 2,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

private object GenreCardDefaults {
    const val CARD_ASPECT_RATIO = 4f
    const val CARD_SHAPE = 8

    val cardShadowGradient = Brush.verticalGradient(
        0f to Color.Black.copy(alpha = 0.4f),
        1f to Color.Black.copy(alpha = 0.8f)
    )
}