package ru.aloyaloya.gametracker.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable
import ru.aloyaloya.gametracker.R
import kotlin.reflect.KClass

// TODO: Move all route resources, classes to feature modules

/**
 * Enumeration representing the top-level navigation destinations in the Gametracker application.
 *
 * Each destination defines the necessary metadata for navigation and UI presentation,
 * including icons, labels, and routing information.
 *
 * @property iconResId The resource ID of the icon to display in the navigation bar.
 * @property labelResId The resource ID of the string label to display for this destination.
 * @property route The primary route class for this destination, used for navigation.
 * @property baseRoute The base route class used for hierarchy checking and selection state.
 */
enum class TopLevelDestination(
    @DrawableRes val iconResId: Int,
    @StringRes val labelResId: Int,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    DISCOVER(
        iconResId = R.drawable.ic_discover_filled,
        labelResId = R.string.discover_screen_title,
        route = DiscoverRoute::class,
        baseRoute = DiscoverBaseRoute::class,
    ),
    SEARCH(
        iconResId = R.drawable.ic_search_filled,
        labelResId = R.string.search_screen_title,
        route = SearchRoute::class,
        baseRoute = SearchBaseRoute::class,
    ),
    COLLECTION(
        iconResId = R.drawable.ic_collection_filled,
        labelResId = R.string.collection_screen_title,
        route = CollectionRoute::class,
        baseRoute = CollectionBaseRoute::class,
    ),
    SETTINGS(
        iconResId = R.drawable.ic_settings_filled,
        labelResId = R.string.settings_screen_title,
        route = SettingsRoute::class,
        baseRoute = SettingsBaseRoute::class,
    ),
}

@Serializable
data object DiscoverRoute

@Serializable
data object DiscoverBaseRoute

@Serializable
data object SearchRoute

@Serializable
data object SearchBaseRoute

@Serializable
data object CollectionRoute

@Serializable
data object CollectionBaseRoute

@Serializable
data object SettingsRoute

@Serializable
data object SettingsBaseRoute
