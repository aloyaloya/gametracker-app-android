package ru.aloyaloya.discover.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.aloyaloya.discover.di.DiscoverComponent
import ru.aloyaloya.discover.presentation.DiscoverRoute
import ru.aloyaloya.discover.presentation.DiscoverViewModel
import ru.aloyaloya.ui.di.ComponentProvider

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
    composable<DiscoverRoute> { navBackStackEntry ->

        val context = LocalContext.current.applicationContext

        val factory = (context as ComponentProvider)
            .provideComponent("discover", DiscoverComponent::class)
            .viewModelFactory

        val viewModel = viewModel<DiscoverViewModel>(
            viewModelStoreOwner = navBackStackEntry,
            factory = factory
        )

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        DiscoverRoute(
            uiState = uiState,
            onEvent = {}
        )
    }
}

