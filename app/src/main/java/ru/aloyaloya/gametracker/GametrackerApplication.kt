package ru.aloyaloya.gametracker

import android.app.Application
import ru.aloyaloya.gametracker.di.AppComponent
import ru.aloyaloya.gametracker.di.DaggerAppComponent

class GametrackerApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)

        appComponent.inject(this)
    }
}