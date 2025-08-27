package ru.aloyaloya.collection.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

/**
 * Represents the navigation route to the Collection screen.
 *
 * This serializable data object serves as a unique identifier for the Collection screen
 * within the navigation graph. It is used to navigate to and compose the Collection screen.
 */
@Serializable
data object CollectionRoute

/**
 * Navigates to the Collection screen with the specified navigation options.
 *
 * @param navOptions the navigation options to configure the navigation behavior,
 * such as animation, pop behavior, and launch mode
 */
fun NavController.navigateToCollection(navOptions: NavOptions) =
    navigate(route = CollectionRoute, navOptions)

/**
 * Defines the Collection screen composable within the navigation graph.
 *
 * This function registers the Collection screen as a composable destination in the
 * navigation graph, associated with the [CollectionRoute]. The content lambda should
 * contain the composable content for the Collection screen.
 *
 * @receiver the NavGraphBuilder used to build the navigation graph
 */
fun NavGraphBuilder.collectionScreen() {
    composable<CollectionRoute> {

    }
}
