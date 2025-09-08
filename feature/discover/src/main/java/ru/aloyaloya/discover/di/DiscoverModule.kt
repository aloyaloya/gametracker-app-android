package ru.aloyaloya.discover.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.aloyaloya.discover.presentation.DiscoverViewModel
import ru.aloyaloya.ui.di.ViewModelKey

@Module
interface DiscoverModule {

    @Binds
    @IntoMap
    @ViewModelKey(DiscoverViewModel::class)
    fun bindsDiscoverViewModel(vm: DiscoverViewModel): ViewModel
}