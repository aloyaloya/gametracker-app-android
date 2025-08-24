package ru.aloyaloya.gametracker.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.util.trace
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import ru.aloyaloya.gametracker.navigation.TopLevelDestination

/**
 * Main application composable that sets up the navigation
 * structure and scaffold for the Gametracker app.
 *
 * This function serves as the root component of the application, initializing the
 * navigation controller and providing the main layout scaffold with bottom navigation.
 * It manages the navigation state and coordinates between different screens of the application.
 *
 * The function uses [rememberNavController] to maintain navigation state across recompositions and
 * [currentBackStackEntryAsState] to observe the current navigation destination.
 */
@Composable
fun GametrackerApp() {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    GametrackerScaffold(
        currentDestination = currentDestination,
        destinations = TopLevelDestination.entries,
        onNavigate = {} // navigateToTopLevelDestination(navController, it)
    ) {
        Text("fff")
    }
}

private fun navigateToTopLevelDestination(
    navController: NavController,
    topLevelDestination: TopLevelDestination
) {
    trace("Navigation: ${topLevelDestination.name}") {
        val topLevelNavOptions = navOptions {
            /**
             * Pop up to the start destination of the graph to
             * avoid building up a large stack of destinations
             * on the back stack as users select items
             * */
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            /**
             * Avoid multiple copies of the same destination when
             * reselecting the same item
             */
            launchSingleTop = true
            /** Restore state when reselecting a previously selected item */
            restoreState = true
        }

        when (topLevelDestination) {
//            TopLevelDestination.DISCOVER -> navController.navigateToDiscover(topLevelNavOptions)
            TopLevelDestination.DISCOVER -> {}
            TopLevelDestination.SEARCH -> {}
            TopLevelDestination.COLLECTION -> {}
            TopLevelDestination.SETTINGS -> {}
        }
    }
}