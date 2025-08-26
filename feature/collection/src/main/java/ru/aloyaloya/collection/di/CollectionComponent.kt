package ru.aloyaloya.collection.di

import androidx.lifecycle.ViewModelProvider
import dagger.Subcomponent

@Subcomponent(
    modules = [
        CollectionModule::class
    ]
)
interface CollectionComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CollectionComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}