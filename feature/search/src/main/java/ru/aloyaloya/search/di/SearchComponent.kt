package ru.aloyaloya.search.di

import androidx.lifecycle.ViewModelProvider
import dagger.Subcomponent

@Subcomponent(
    modules = [
        SearchModule::class
    ]
)
interface SearchComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SearchComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}