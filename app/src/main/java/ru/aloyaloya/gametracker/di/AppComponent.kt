package ru.aloyaloya.gametracker.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import ru.aloyaloya.gametracker.GametrackerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(application: GametrackerApplication)

    val viewModelFactory: ViewModelProvider.Factory
}