package ru.aloyaloya.discover.di

import androidx.lifecycle.ViewModelProvider
import dagger.Subcomponent

@Subcomponent(
    modules = [
        DiscoverModule::class
    ]
)
interface DiscoverComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DiscoverComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}