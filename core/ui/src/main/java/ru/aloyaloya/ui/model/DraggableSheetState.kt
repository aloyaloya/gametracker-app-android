package ru.aloyaloya.ui.model

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp

/**
 * State class that manages the position and behavior of a draggable sheet component.
 *
 * @param headerHeight The height of the sheet's header section
 * @param collapsedOffset The offset value when the sheet is fully collapsed
 * @param listState The lazy list state for the sheet's content area
 */
@Stable
class DraggableSheetState internal constructor(
    headerHeight: Dp,
    private val collapsedOffset: Float,
    val listState: LazyListState
) {
    val headerHeightDp = headerHeight

    private val animatable = Animatable(collapsedOffset)
    var offsetPx by mutableFloatStateOf(collapsedOffset)

    /**
     * Indicates whether the sheet is fully expanded.
     */
    val isExpanded: Boolean get() = offsetPx == DraggableSheetStateDefaults.MIN_OFFSET

    /**
     * Indicates whether the sheet is fully collapsed.
     */
    val isCollapsed: Boolean get() = offsetPx == collapsedOffset

    /**
     * Progress value between collapsed (0f) and expanded (1f) states.
     */
    val progress: Float
        get() = (offsetPx / collapsedOffset).coerceIn(
            DraggableSheetStateDefaults.MIN_PROGRESS,
            DraggableSheetStateDefaults.MAX_PROGRESS
        )

    /**
     * Expands the sheet to its fully open position.
     */
    suspend fun expand() = animateTo(DraggableSheetStateDefaults.MIN_OFFSET)

    /**
     * Collapses the sheet to its fully closed position.
     */
    suspend fun collapse() = animateTo(collapsedOffset)

    /**
     * Animates the sheet to the specified target offset.
     *
     * @param target The target offset value to animate to
     */
    suspend fun animateTo(target: Float) {
        animatable.snapTo(offsetPx)
        animatable.animateTo(
            targetValue = target,
            animationSpec = spring(stiffness = DraggableSheetStateDefaults.SPRING_STIFFNESS)
        ) {
            offsetPx = value
        }
    }

    /**
     * Applies a delta value to the current offset, clamping within valid bounds.
     *
     * @param delta The change in offset to apply
     * @return The amount of delta that was actually consumed
     */
    fun applyDelta(delta: Float): Float {
        val prev = offsetPx
        val new = (prev + delta).coerceIn(
            DraggableSheetStateDefaults.MIN_OFFSET,
            collapsedOffset
        )
        offsetPx = new
        return new - prev
    }

    /**
     * Calculates the nearest anchor point (either expanded or collapsed) based on current position.
     *
     * @return The offset of the nearest anchor point
     */
    fun nearestAnchor(): Float =
        if (offsetPx < collapsedOffset * DraggableSheetStateDefaults.ANCHOR_THRESHOLD) {
            DraggableSheetStateDefaults.MIN_OFFSET
        } else {
            collapsedOffset
        }
}

/**
 * Default values for [DraggableSheetState] configuration.
 */
private object DraggableSheetStateDefaults {
    const val MIN_OFFSET = 0f
    const val MIN_PROGRESS = 0f
    const val MAX_PROGRESS = 1f
    const val SPRING_STIFFNESS = 600f
    const val ANCHOR_THRESHOLD = 0.5f
}