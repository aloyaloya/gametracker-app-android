package ru.aloyaloya.design_system.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.aloyaloya.design_system.component.card.StatusChipDefaults.CHIP_WITH_LABEL_HORIZONTAL_PADDING
import ru.aloyaloya.design_system.component.card.StatusChipDefaults.DEFAULT_CHIP_HORIZONTAL_PADDING
import ru.aloyaloya.design_system.component.card.StatusChipDefaults.DEFAULT_CHIP_ICON_SIZE
import ru.aloyaloya.design_system.component.card.StatusChipDefaults.DEFAULT_CHIP_ITEMS_SPACER
import ru.aloyaloya.design_system.component.card.StatusChipDefaults.DEFAULT_CHIP_SHAPE
import ru.aloyaloya.design_system.component.card.StatusChipDefaults.DEFAULT_CHIP_VERTICAL_PADDING
import ru.aloyaloya.domain.model.GameStatus

/**
 * A compact chip component that visually represents a game's status.
 *
 * This chip displays an icon representing the game status and optionally shows
 * a text label alongside it. The component uses a tertiary color scheme from
 * the current theme for its background and content.
 *
 * @param status The [GameStatus] to display. Determines the icon and optional text.
 * @param textVisible Whether the text label should be visible alongside the icon.
 */
@Composable
fun StatusChip(
    status: GameStatus,
    textVisible: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(DEFAULT_CHIP_SHAPE.dp)
            )
            .padding(
                horizontal = if (textVisible) {
                    CHIP_WITH_LABEL_HORIZONTAL_PADDING.dp
                } else {
                    DEFAULT_CHIP_HORIZONTAL_PADDING.dp
                },
                vertical = DEFAULT_CHIP_VERTICAL_PADDING.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(DEFAULT_CHIP_ITEMS_SPACER.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (textVisible) {
            Text(
                text = stringResource(status.labelResId),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }

        Icon(
            modifier = Modifier.size(DEFAULT_CHIP_ICON_SIZE.dp),
            painter = painterResource(status.iconResId),
            tint = MaterialTheme.colorScheme.onTertiary,
            contentDescription = null
        )
    }
}

/**
 * Default values for [StatusChip] styling and dimensions.
 */
private object StatusChipDefaults {
    const val DEFAULT_CHIP_VERTICAL_PADDING = 4
    const val DEFAULT_CHIP_HORIZONTAL_PADDING = 4
    const val DEFAULT_CHIP_SHAPE = 8
    const val DEFAULT_CHIP_ITEMS_SPACER = 4
    const val DEFAULT_CHIP_ICON_SIZE = 20
    const val CHIP_WITH_LABEL_HORIZONTAL_PADDING = 8
}

