package ru.aloyaloya.discover.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

/**
 * Represents the navigation route to the Discover screen.
 *
 * This serializable data object serves as a unique identifier for the Discover screen
 * within the navigation graph. It is used to navigate to and compose the Discover screen.
 */
@Serializable
data object DiscoverRoute

/**
 * Navigates to the Discover screen with the specified navigation options.
 *
 * @param navOptions the navigation options to configure the navigation behavior,
 * such as animation, pop behavior, and launch mode
 */
fun NavController.navigateToDiscover(navOptions: NavOptions) =
    navigate(route = DiscoverRoute, navOptions)

/**
 * Defines the Discover screen composable within the navigation graph.
 *
 * This function registers the Discover screen as a composable destination in the
 * navigation graph, associated with the [DiscoverRoute]. The content lambda should
 * contain the composable content for the Discover screen.
 *
 * @receiver the NavGraphBuilder used to build the navigation graph
 */
fun NavGraphBuilder.discoverScreen() {
    composable<DiscoverRoute> {

    }
}

