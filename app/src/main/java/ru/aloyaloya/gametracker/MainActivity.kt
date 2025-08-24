package ru.aloyaloya.gametracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.aloyaloya.design_system.theme.GametrackerTheme
import ru.aloyaloya.gametracker.ui.GametrackerApp

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appComponent = (application as GametrackerApplication).appComponent

        viewModel = appComponent.viewModelFactory.create(MainActivityViewModel::class.java)

        enableEdgeToEdge()
        setContent {
            GametrackerTheme(darkTheme = false, dynamicColor = false) {
                GametrackerApp()
            }
        }
    }
}
