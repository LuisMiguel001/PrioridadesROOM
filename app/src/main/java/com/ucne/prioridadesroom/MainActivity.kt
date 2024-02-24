package com.ucne.prioridadesroom

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucne.prioridadesroom.ui.Prioridad.RegistroScreen
import com.ucne.prioridadesroom.ui.consulta.Consulta
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.unit.dp
import com.ucne.prioridadesroom.ui.home.Home
import com.ucne.prioridadesroom.ui.theme.PrioridadesROOMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrioridadesROOMTheme(darkTheme = true){
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Priorirdad DB")
                            },
                            actions = {
                                IconButton(
                                    onClick = {
                                        navController.navigate("home")
                                    }
                                ) {
                                    Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                                }
                                IconButton(
                                    onClick = {
                                        navController.navigate("registro")
                                    }
                                ) {
                                    Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Prioridad")
                                }
                                IconButton(
                                    onClick = {
                                        navController.navigate("Consulta")
                                    }
                                ) {
                                    Icon(imageVector = Icons.Default.List, contentDescription = "Consulta Prioridades")
                                }
                            }
                        )
                    }
                ){
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.fillMaxSize()
                    ) {
                        composable("home") {
                            Home()
                        }
                        composable("registro") {
                            RegistroScreen()
                        }
                        composable("Consulta") {
                            Consulta()
                        }
                    }
                }
            }
        }
    }
}
