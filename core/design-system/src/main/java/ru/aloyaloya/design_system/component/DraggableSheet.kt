package ru.aloyaloya.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.aloyaloya.ui.model.DraggableSheetState
import ru.aloyaloya.ui.utils.draggableSheetNestedScrollConnection
import kotlin.math.roundToInt

/**
 * A draggable sheet component that provides a draggable header and scrollable content area.
 *
 * This composable displays a header section that can be dragged to expand/collapse the sheet
 * and a lazy column content area that moves accordingly. Includes a shadow gradient overlay
 * for visual enhancement.
 *
 * @param state The state object that controls the sheet's position and behavior
 * @param headerContent Composable content for the draggable header section
 * @param sheetContent Content for the lazy column sheet area using LazyListScope
 * @param modifier Optional modifier for the root layout
 */
@Composable
fun DraggableSheet(
    state: DraggableSheetState,
    headerContent: @Composable () -> Unit,
    sheetContent: LazyListScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val headerDragState = rememberDraggableState { dy ->
        state.applyDelta(dy)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(draggableSheetNestedScrollConnection(state))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(state.headerHeightDp)
                .alpha(state.progress)
                .draggable(
                    state = headerDragState,
                    orientation = Orientation.Vertical,
                    onDragStopped = { state.handleDragStopped(scope, it) }
                )
        ) {
            headerContent()
        }

        LazyColumn(
            state = state.listState,
            verticalArrangement = Arrangement.spacedBy(DraggableSheetDefaults.CONTENT_SPACING.dp),
            modifier = Modifier
                .offset { IntOffset(0, state.offsetPx.roundToInt()) }
                .fillMaxSize()
        ) {
            sheetContent()
        }

        // Shadow gradient overlay
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(state.progress)
                .background(DraggableSheetDefaults.cardShadowGradient)
        )
    }
}

/**
 * Handles the drag stopped event for the draggable sheet.
 * Animates to the appropriate position based on velocity and current state.
 */
private fun DraggableSheetState.handleDragStopped(
    scope: CoroutineScope,
    velocity: Float
) {
    scope.launch {
        when {
            velocity < -DraggableSheetDefaults.VELOCITY_THRESHOLD && !isExpanded -> expand()
            velocity > DraggableSheetDefaults.VELOCITY_THRESHOLD && !isCollapsed -> collapse()
            else -> animateTo(nearestAnchor())
        }
    }
}

/**
 * Default values for [DraggableSheet] component.
 */
private object DraggableSheetDefaults {
    const val VELOCITY_THRESHOLD = 50
    const val CONTENT_SPACING = 16

    private const val SHADOW_START_ALPHA = 0.6f
    private const val SHADOW_END_ALPHA = 1f

    val cardShadowGradient = Brush.verticalGradient(
        SHADOW_START_ALPHA to Color.Transparent,
        SHADOW_END_ALPHA to Color.Black.copy(alpha = 0.8f)
    )
}