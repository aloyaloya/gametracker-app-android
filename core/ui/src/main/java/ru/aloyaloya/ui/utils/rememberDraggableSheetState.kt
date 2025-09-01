package ru.aloyaloya.ui.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import ru.aloyaloya.ui.model.DraggableSheetState

/**
 * Creates and remembers a [DraggableSheetState] for use in a [DraggableSheet] component.
 *
 * This function provides a convenient way to create and remember the state object that
 * manages the position and behavior of a draggable sheet.
 *
 * @param headerHeight The height of the sheet's header section in [Dp]
 * @param listState The lazy list state for the sheet's content area, defaults to a new remembered state
 * @return A remembered [DraggableSheetState] instance
 */
@Composable
fun rememberDraggableSheetState(
    headerHeight: Dp,
    listState: LazyListState = rememberLazyListState(),
): DraggableSheetState {
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }

    return remember(headerHeightPx, listState) {
        DraggableSheetState(headerHeight, headerHeightPx, listState)
    }
}