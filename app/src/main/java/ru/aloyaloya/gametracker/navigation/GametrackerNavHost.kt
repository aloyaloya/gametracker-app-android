package ru.aloyaloya.gametracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.aloyaloya.collection.presentation.navigation.collectionScreen
import ru.aloyaloya.discover.presentation.navigation.DiscoverRoute
import ru.aloyaloya.discover.presentation.navigation.discoverScreen
import ru.aloyaloya.search.presentation.navigation.searchScreen
import ru.aloyaloya.settings.presentation.navigation.settingsScreen

/**
 * Top-level navigation graph. Navigation is organized as explained at
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun GametrackerNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DiscoverRoute,
        modifier = modifier
    ) {
        discoverScreen()
        searchScreen()
        collectionScreen()
        settingsScreen()
    }
}