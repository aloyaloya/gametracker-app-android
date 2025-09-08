package ru.aloyaloya.ui.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.unit.Velocity
import ru.aloyaloya.ui.model.DraggableSheetState

/**
 * Creates a [NestedScrollConnection] for the draggable sheet that handles nested scrolling behavior.
 *
 * This connection manages the interaction between the sheet and nested scrollable content,
 * allowing the sheet to intercept scroll events based on its current state and position.
 *
 * @param state The state object that controls the sheet's position and behavior
 * @return A configured [NestedScrollConnection] instance
 */
fun draggableSheetNestedScrollConnection(state: DraggableSheetState): NestedScrollConnection =
    object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            if (source != NestedScrollSource.UserInput) return Offset.Zero

            return handleScrollInput(available.y, source)
        }

        override fun onPostScroll(
            consumed: Offset,
            available: Offset,
            source: NestedScrollSource
        ): Offset {
            if (source != NestedScrollSource.UserInput) return Offset.Zero

            return Offset(0f, handleScrollInput(available.y, source).y)
        }

        override suspend fun onPreFling(available: Velocity): Velocity {
            return handleFling(available.y)
        }

        override suspend fun onPostFling(
            consumed: Velocity,
            available: Velocity
        ): Velocity {
            return handlePostFling()
        }

        private fun handleScrollInput(dy: Float, source: NestedScrollSource): Offset {
            // Scroll up - sheet intercepts if not expanded
            if (dy < 0 && !state.isExpanded) {
                val consumed = state.applyDelta(dy)
                return Offset(0f, consumed)
            }

            // Scroll down - sheet intercepts if list at top and sheet not collapsed
            val childAtTop =
                state.listState.firstVisibleItemIndex == DraggableSheetScrollDefaults.FIRST_ITEM_INDEX &&
                        state.listState.firstVisibleItemScrollOffset == DraggableSheetScrollDefaults.TOP_OFFSET

            if (dy > 0 && childAtTop && !state.isCollapsed) {
                val consumed = state.applyDelta(dy)
                return Offset(0f, consumed)
            }

            return Offset.Zero
        }

        private suspend fun handleFling(vy: Float): Velocity {
            val childAtTop =
                state.listState.firstVisibleItemIndex == DraggableSheetScrollDefaults.FIRST_ITEM_INDEX &&
                        state.listState.firstVisibleItemScrollOffset == DraggableSheetScrollDefaults.TOP_OFFSET

            when {
                vy < -DraggableSheetScrollDefaults.FLING_VELOCITY_THRESHOLD && !state.isExpanded -> {
                    state.expand()
                    return Velocity(0f, vy)
                }

                vy > DraggableSheetScrollDefaults.FLING_VELOCITY_THRESHOLD && !state.isCollapsed && childAtTop -> {
                    state.collapse()
                    return Velocity(0f, vy)
                }

                else -> return Velocity.Zero
            }
        }

        private suspend fun handlePostFling(): Velocity {
            if (!state.isExpanded && !state.isCollapsed) {
                state.animateTo(state.nearestAnchor())
            }
            return Velocity.Zero
        }
    }

/**
 * Default values for draggable sheet scroll behavior.
 */
private object DraggableSheetScrollDefaults {
    const val FLING_VELOCITY_THRESHOLD = 50
    const val FIRST_ITEM_INDEX = 0
    const val TOP_OFFSET = 0
}