package ru.aloyaloya.gametracker

import android.app.Application
import ru.aloyaloya.gametracker.di.AppComponent
import ru.aloyaloya.gametracker.di.ComponentProvider
import ru.aloyaloya.gametracker.di.DaggerAppComponent
import kotlin.reflect.KClass

class GametrackerApplication : ComponentProvider, Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)

        appComponent.inject(this)
    }

    override fun <T : Any> provideComponent(key: String, clazz: KClass<T>): T {
        return when (key) {
            "discover" -> appComponent.discoverComponentFactory.create() as T
            "search" -> appComponent.searchComponentFactory.create() as T
            "collection" -> appComponent.collectionComponentFactory.create() as T
            "settings" -> appComponent.settingsComponentFactory.create() as T
            else -> throw IllegalArgumentException("Unknown component key: $key")
        }
    }
}