package com.ucne.prioridadesroom.ui.Prioridad

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun RegistroScreen(
    viewModel: PrioridadViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val prioridad = state.prioridades

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Registro de Prioridad", style = MaterialTheme.typography.bodyMedium)

        state.succesMessage?.let {
            Text(
                text = it,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = prioridad.idPrioridad.toString(),
            onValueChange = { viewModel.onEvent(PrioridadEvent.IdPrioridad(it)) },
            label = { Text(text = "ID: ") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        )

        OutlinedTextField(
            value = prioridad.nombre,
            onValueChange = { viewModel.onEvent(PrioridadEvent.Nombre(it)) },
            label = { Text(text = "Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        OutlinedTextField(
            value = prioridad.descripcion,
            onValueChange = { viewModel.onEvent(PrioridadEvent.Descripcion(it)) },
            label = { Text(text = "Descriopción") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        OutlinedTextField(
            value = prioridad.plazo.toString(),
            onValueChange = { viewModel.onEvent(PrioridadEvent.Plazo(it)) },
            label = { Text(text = "Plazo: ") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        )

        OutlinedTextField(
            value = prioridad.esNulo.toString(),
            onValueChange = { viewModel.onEvent(PrioridadEvent.IsNull(it)) },
            label = { Text(text = "Es Nulo: ") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        )

        OutlinedTextField(
            value = prioridad.Creador.toString(),
            onValueChange = { viewModel.onEvent(PrioridadEvent.CreadoPor(it)) },
            label = { Text(text = "Creado Por: ") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        )

        OutlinedTextField(
            value = prioridad.fechaCreacion,
            onValueChange = { viewModel.onEvent(PrioridadEvent.FechaCreacion(it)) },
            label = { Text(text = "Fecha Modificación: ") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        )

        OutlinedTextField(
            value = prioridad.modidicador.toString(),
            onValueChange = { viewModel.onEvent(PrioridadEvent.ModificadoPor(it)) },
            label = { Text(text = "Modificado Por: ") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        )

        OutlinedTextField(
            value = prioridad.fechaModificacion,
            onValueChange = { viewModel.onEvent(PrioridadEvent.FechaModificaion(it)) },
            label = { Text(text = "Fecha de Modificación: ") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    viewModel.onEvent(PrioridadEvent.onSave)
                    viewModel.onEvent(PrioridadEvent.onNew)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Guardar")
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    }
}