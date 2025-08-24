package ru.aloyaloya.gametracker.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import ru.aloyaloya.gametracker.GametrackerApplication
import javax.inject.Singleton

/**
 * Main Dagger component for the Gametracker application.
 *
 * This component serves as the root dependency injection container for the application,
 * providing singleton-scoped dependencies throughout the app. It defines the application-wide
 * dependency graph and binds the application context for use in dependency provision.
 *
 * @Singleton Ensures that dependencies provided by this component are singleton-scoped
 * and maintained for the lifetime of the application.
 */
@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    /**
     * Factory interface for creating instances of [AppComponent] with required dependencies.
     *
     * This factory pattern allows for parameterized component creation where the application
     * context is provided at instantiation time and bound for dependency injection.
     *
     * @param context The application context to be bound into the dependency graph.
     * @return A fully configured [AppComponent] instance.
     */
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    /**
     * Performs field injection into the [GametrackerApplication] instance.
     *
     * This method is used to inject dependencies into the application class itself,
     * typically for initializing application-wide components and services.
     *
     * @param application The application instance to inject dependencies into.
     */
    fun inject(application: GametrackerApplication)

    /**
     * Provides a [ViewModelProvider.Factory] for ViewModel dependency injection.
     *
     * This factory enables ViewModels to receive their dependencies through constructor
     * injection, following the Android Architecture Components guidelines.
     *
     * @return A factory capable of creating ViewModels with injected dependencies.
     */
    val viewModelFactory: ViewModelProvider.Factory
}