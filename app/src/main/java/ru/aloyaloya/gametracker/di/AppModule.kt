package ru.aloyaloya.gametracker.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.aloyaloya.gametracker.MainActivityViewModel
import ru.aloyaloya.ui.di.DaggerVMFactory
import ru.aloyaloya.ui.di.ViewModelKey

@Module(
    includes = [],
    subcomponents = []
)
interface AppModule {

    @Binds
    fun bindsViewModelFactory(daggerVMFactory: DaggerVMFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindsMainViewModel(vm: MainActivityViewModel): ViewModel
}