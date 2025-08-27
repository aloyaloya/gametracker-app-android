package ru.aloyaloya.gametracker.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.aloyaloya.collection.di.CollectionComponent
import ru.aloyaloya.discover.di.DiscoverComponent
import ru.aloyaloya.gametracker.MainActivityViewModel
import ru.aloyaloya.search.di.SearchComponent
import ru.aloyaloya.settings.di.SettingsComponent
import ru.aloyaloya.ui.di.DaggerVMFactory
import ru.aloyaloya.ui.di.ViewModelKey

/**
 * Dagger module for providing application-wide dependencies in the Gametracker application.
 *
 * This module defines bindings for ViewModel-related dependencies and serves as the central
 * configuration point for application-scoped components. It uses multibindings to provide
 * ViewModels through a generic [ViewModelProvider.Factory].
 *
 * The module currently includes:
 * - Binding for Dagger-based ViewModel factory
 * - Multibinding for specific ViewModel classes
 */
@Module(
    includes = [],
    subcomponents = [
        DiscoverComponent::class,
        SearchComponent::class,
        CollectionComponent::class,
        SettingsComponent::class
    ]
)
interface AppModule {

    /**
     * Binds the custom Dagger ViewModel factory to the Android Framework's [ViewModelProvider.Factory].
     *
     * This binding allows Dagger to provide ViewModels with constructor injection through
     * the standard Android ViewModel provider mechanism.
     *
     * @param daggerVMFactory The Dagger-specific ViewModel factory implementation.
     * @return A [ViewModelProvider.Factory] that can create ViewModels with injected dependencies.
     */
    @Binds
    fun bindsViewModelFactory(daggerVMFactory: DaggerVMFactory): ViewModelProvider.Factory

    /**
     * Multibinding for [MainActivityViewModel] to be provided through the ViewModel factory.
     *
     * This binding adds [MainActivityViewModel] to the multibinding map, allowing the
     * Dagger ViewModel factory to instantiate it with proper dependency injection.
     *
     * @param vm The instance of [MainActivityViewModel] to be provided.
     * @return A [ViewModel] for inclusion in the multibinding map.
     */
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindsMainViewModel(vm: MainActivityViewModel): ViewModel
}