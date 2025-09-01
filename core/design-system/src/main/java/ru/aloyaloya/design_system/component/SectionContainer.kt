package ru.aloyaloya.design_system.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// TODO: Add KDoc

@Composable
fun SectionContainer(
    title: String,
    onShowMore: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            SectionContainerDefaults.INNER_CONTENT_SPACER.dp
        )
    ) {
        SectionHeader(
            title = title,
            onShowMore = onShowMore,
            modifier = Modifier.padding(
                horizontal = SectionContainerDefaults.HEADER_HORIZONTAL_PADDING.dp
            )
        )

        content()
    }
}

private object SectionContainerDefaults {
    const val HEADER_HORIZONTAL_PADDING = 24
    const val INNER_CONTENT_SPACER = 16
}