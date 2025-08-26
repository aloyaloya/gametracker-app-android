package ru.aloyaloya.settings.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

/**
 * Represents the navigation route to the Settings screen.
 *
 * This serializable data object serves as a unique identifier for the Settings screen
 * within the navigation graph. It is used to navigate to and compose the Settings screen.
 */
@Serializable
data object SettingsRoute

/**
 * Navigates to the Settings screen with the specified navigation options.
 *
 * @param navOptions the navigation options to configure the navigation behavior,
 * such as animation, pop behavior, and launch mode
 */
fun NavController.navigateToSettings(navOptions: NavOptions) =
    navigate(route = SettingsRoute, navOptions)

/**
 * Defines the Settings screen composable within the navigation graph.
 *
 * This function registers the Settings screen as a composable destination in the
 * navigation graph, associated with the [SettingsRoute]. The content lambda should
 * contain the composable content for the Settings screen.
 *
 * @receiver the NavGraphBuilder used to build the navigation graph
 */
fun NavGraphBuilder.settingsScreen() {
    composable<SettingsRoute> {

    }
}