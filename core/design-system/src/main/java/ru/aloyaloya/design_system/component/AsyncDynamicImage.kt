package ru.aloyaloya.design_system.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

// TODO: Add loading, error states placeholder

/**
 * A reusable composable for asynchronously loading and displaying images with smooth transitions.
 *
 * This component wraps [SubcomposeAsyncImage] from Coil library with predefined sensible defaults
 * optimized for game cover images and dynamic content. It includes crossfade animation for smooth
 * image loading transitions and uses crop scaling to maintain aspect ratio while filling available space.
 *
 * @param imageUrl The URL of the image to load and display. Supports various formats including web URLs.
 * @param modifier The [Modifier] to be applied to the image container.
 * Note that [Modifier.fillMaxSize()]is always applied to ensure the image fills the available space.
 */
@Composable
fun AsyncDynamicImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(300)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.fillMaxSize()
    )
}