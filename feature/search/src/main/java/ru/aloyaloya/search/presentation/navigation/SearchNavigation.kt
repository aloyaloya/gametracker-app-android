package ru.aloyaloya.search.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

/**
 * Represents the navigation route to the Search screen.
 *
 * This serializable data object serves as a unique identifier for the Search screen
 * within the navigation graph. It is used to navigate to and compose the Search screen.
 */
@Serializable
data object SearchRoute

/**
 * Navigates to the Search screen with the specified navigation options.
 *
 * @param navOptions the navigation options to configure the navigation behavior,
 * such as animation, pop behavior, and launch mode
 */
fun NavController.navigateToSearch(navOptions: NavOptions) =
    navigate(route = SearchRoute, navOptions)

/**
 * Defines the Search screen composable within the navigation graph.
 *
 * This function registers the Search screen as a composable destination in the
 * navigation graph, associated with the [SearchRoute]. The content lambda should
 * contain the composable content for the Search screen.
 *
 * @receiver the NavGraphBuilder used to build the navigation graph
 */
fun NavGraphBuilder.searchScreen() {
    composable<SearchRoute> {

    }
}
