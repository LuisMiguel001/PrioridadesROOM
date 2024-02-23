package com.ucne.prioridadesroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Lifecycle
import androidx.room.Room
import com.ucne.prioridadesroom.data.database.PrioridadDatabase
import com.ucne.prioridadesroom.ui.Prioridad.PrioridadViewModel
import com.ucne.prioridadesroom.ui.Prioridad.RegistroScreen
import com.ucne.prioridadesroom.ui.theme.PrioridadesROOMTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrioridadesROOMTheme {
                RegistroScreen()
            }
        }
    }
}