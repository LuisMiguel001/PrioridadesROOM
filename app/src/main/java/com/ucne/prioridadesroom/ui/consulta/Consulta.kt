package com.ucne.prioridadesroom.ui.consulta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ucne.prioridadesroom.data.entity.PrioridadEntity
import com.ucne.prioridadesroom.ui.Prioridad.PrioridadEvent
import com.ucne.prioridadesroom.ui.Prioridad.PrioridadViewModel

@Composable
fun Consulta(
    viewModel: PrioridadViewModel = hiltViewModel()
) {
    val prioridades by viewModel.prioridades.collectAsState(initial = emptyList())

    LazyColumn(
        contentPadding = PaddingValues(top = 50.dp)
    ) {
        items(prioridades) { prioridad ->
            ExpandableCard(prioridad = prioridad, onDeleteClick = {
                viewModel.onEvent(PrioridadEvent.onDelete(prioridad))
            })
        }
    }
}

@Composable
fun ExpandableCard(
    prioridad: PrioridadEntity,
    onDeleteClick: () -> Unit

) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "ID: ${prioridad.idPrioridad}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Nombre: ${prioridad.nombre}",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                IconButton(
                    onClick = { onDeleteClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar"
                    )
                }
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Descripción: ${prioridad.descripcion}",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Plazo: ${prioridad.plazo}",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Es Nulo: ${prioridad.esNulo}",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Creado Por: ${prioridad.Creador}",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Fecha de Creación: ${prioridad.fechaCreacion}",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Modificado Por: ${prioridad.modidicador}",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Fecha de Modificación: ${prioridad.fechaModificacion}",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            IconButton(
                onClick = { isExpanded = !isExpanded }
            ) {
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            }
        }
    }
}