package ru.aloyaloya.gametracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import ru.aloyaloya.design_system.component.navigation.BottomNavigationBar
import ru.aloyaloya.design_system.component.navigation.BottomNavigationBarItem
import ru.aloyaloya.gametracker.navigation.TopLevelDestination
import kotlin.reflect.KClass

/**
 * A custom Scaffold component for the Gametracker application
 * with gradient background and bottom navigation.
 *
 * This composable provides a scaffold layout with a vertical
 * gradient background and a bottom navigation bar.
 *
 * It automatically handles the selection state
 * of navigation items based on the current destination.
 *
 * @param currentDestination The current navigation destination from the NavController.
 * @param destinations A list of [TopLevelDestination] objects.
 * @param onNavigate A callback function invoked when a navigation item is clicked.
 * @param modifier The [Modifier] to be applied to the underlying Box container.
 * @param content The main content of the screen.
 */
@Composable
fun GametrackerScaffold(
    currentDestination: NavDestination?,
    destinations: List<TopLevelDestination>,
    onNavigate: (TopLevelDestination) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    0.0f to MaterialTheme.colorScheme.surfaceContainerLow, // 0%
                    0.3f to MaterialTheme.colorScheme.surfaceContainerHigh, // 20%
                    0.7f to MaterialTheme.colorScheme.surfaceContainer // 60%
                )
            )
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar {
                    destinations.forEach { destination ->
                        BottomNavigationBarItem(
                            painter = painterResource(destination.iconResId),
                            label = stringResource(destination.labelResId),
                            selected = currentDestination.isRouteInHierarchy(destination.baseRoute),
                            onClick = { onNavigate(destination) }
                        )
                    }
                }
            },

            /** Make it transparent so that the Box gradient is visible */
            containerColor = Color.Transparent
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                content()
            }
        }
    }
}

/**
 * This extension function traverses the hierarchy of navigation destinations starting from
 * the current destination and checks if any destination matches the specified route class.
 *
 * @param route The Kotlin class representing the navigation route to check for in the hierarchy.
 * @return `true` if the route is found in the current destination's hierarchy, `false` otherwise.
 */
private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any { it.hasRoute(route) } ?: false
