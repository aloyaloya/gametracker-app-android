package ru.aloyaloya.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.aloyaloya.design_system.component.StandardCardDefaults.DEFAULT_PRIMARY_BUTTON_HORIZONTAL_PADDING
import ru.aloyaloya.design_system.component.StandardCardDefaults.DEFAULT_PRIMARY_BUTTON_SHAPE
import ru.aloyaloya.design_system.component.StandardCardDefaults.DEFAULT_PRIMARY_BUTTON_VERTICAL_PADDING

/**
 * A customized primary button component with gradient background and consistent styling.
 *
 * This button serves as the main call-to-action element in the application. It features
 * a linear gradient background using the theme's primary color palette, transparent container
 * to allow the gradient to be visible, and standardized padding and shape for consistency.
 *
 * The button automatically handles enabled/disabled states with appropriate visual styling.
 *
 * @param text The text to display on the button.
 * @param onClick The callback to be invoked when the button is clicked.
 * @param modifier The [Modifier] to be applied to the button container.
 * @param enabled Controls the enabled state of the button.
 */
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .background(
                shape = RoundedCornerShape(DEFAULT_PRIMARY_BUTTON_SHAPE.dp),
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.inversePrimary
                    )
                )
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = Color.Transparent
        ),
        enabled = enabled,
        contentPadding = PaddingValues(
            horizontal = DEFAULT_PRIMARY_BUTTON_HORIZONTAL_PADDING.dp,
            vertical = DEFAULT_PRIMARY_BUTTON_VERTICAL_PADDING.dp
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

/**
 * Default values for [PrimaryButton] styling and dimensions.
 *
 * Contains constants for button shape, padding, and other visual properties that ensure
 * consistency across all primary buttons in the application.
 */
private object StandardCardDefaults {
    const val DEFAULT_PRIMARY_BUTTON_VERTICAL_PADDING = 12
    const val DEFAULT_PRIMARY_BUTTON_HORIZONTAL_PADDING = 24
    const val DEFAULT_PRIMARY_BUTTON_SHAPE = 16
}