package ru.aloyaloya.settings.di

import androidx.lifecycle.ViewModelProvider
import dagger.Subcomponent

@Subcomponent(
    modules = [
        DiscoverModule::class
    ]
)
interface SettingsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingsComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}