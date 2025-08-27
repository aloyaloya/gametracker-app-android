package ru.aloyaloya.gametracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.util.trace
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import ru.aloyaloya.collection.presentation.navigation.navigateToCollection
import ru.aloyaloya.discover.presentation.navigation.navigateToDiscover
import ru.aloyaloya.gametracker.navigation.GametrackerNavHost
import ru.aloyaloya.gametracker.navigation.TopLevelDestination
import ru.aloyaloya.search.presentation.navigation.navigateToSearch
import ru.aloyaloya.settings.presentation.navigation.navigateToSettings

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
internal fun GametrackerApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    GametrackerScaffold(
        currentDestination = currentDestination,
        destinations = TopLevelDestination.entries,
        onNavigate = { navigateToTopLevelDestination(navController, it) }
    ) {
        GametrackerNavHost(navController = navController)
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
            TopLevelDestination.DISCOVER -> navController.navigateToDiscover(topLevelNavOptions)
            TopLevelDestination.SEARCH -> navController.navigateToSearch(topLevelNavOptions)
            TopLevelDestination.COLLECTION -> navController.navigateToCollection(topLevelNavOptions)
            TopLevelDestination.SETTINGS -> navController.navigateToSettings(topLevelNavOptions)
        }
    }
}