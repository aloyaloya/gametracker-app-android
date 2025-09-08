package ru.aloyaloya.design_system.component.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.aloyaloya.ui.model.TopBarAction

// TODO: Add KDoc, defaults object

@Composable
fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    leftAction: TopBarAction? = null,
    rightAction: TopBarAction? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(
                top = 0.dp,
                bottom = 24.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = leftAction != null,
            enter = scaleIn(),
            exit = scaleOut(),
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            leftAction?.let { action ->
                ActionButton(
                    onClick = { action.actionUnit.invoke() },
                    iconRes = action.iconResId,
                    stringRes = action.descriptionResId,
                    filled = action.actionFilled
                )
            }
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.align(Alignment.Center)
        )

        AnimatedVisibility(
            visible = rightAction != null,
            enter = scaleIn(),
            exit = scaleOut(),
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            rightAction?.let { action ->
                ActionButton(
                    onClick = { action.actionUnit.invoke() },
                    iconRes = action.iconResId,
                    stringRes = action.descriptionResId,
                    filled = action.actionFilled
                )
            }
        }
    }
}

@Composable
private fun ActionButton(
    onClick: () -> Unit,
    iconRes: Int,
    stringRes: Int,
    filled: Boolean,
    modifier: Modifier = Modifier
) {
    FilledIconButton(
        modifier = modifier,
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = if (filled) MaterialTheme.colorScheme.primary else Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Crossfade(targetState = iconRes, label = "ActionButtonCrossfade") { targetIcon ->
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(targetIcon),
                contentDescription = stringResource(stringRes)
            )
        }
    }
}