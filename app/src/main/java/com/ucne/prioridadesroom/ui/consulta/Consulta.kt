package com.ucne.prioridadesroom.ui.consulta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.prioridadesroom.data.entity.PrioridadEntity
import com.ucne.prioridadesroom.ui.Prioridad.PrioridadEvent
import com.ucne.prioridadesroom.ui.Prioridad.PrioridadViewModel

@Composable
fun Consulta(
    viewModel: PrioridadViewModel = hiltViewModel()
) {
    val prioridades by viewModel.prioridades.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(text = "Consulta de Prioridades", style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = Color.Green
        )

        LazyColumn{
            items(prioridades) { prioridad ->
                ExpandableCard(prioridad = prioridad, onDeleteClick = {
                    viewModel.onEvent(PrioridadEvent.onDelete(prioridad))
                })
            }
        }
    }
}

@Composable
fun ExpandableCard(
    prioridad: PrioridadEntity,
    onDeleteClick: () -> Unit

) {
    var isExpanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

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
                Column{
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                                append("ID: ")
                            }
                            withStyle(style = SpanStyle(color = Color.Green)) {
                                append("${prioridad.idPrioridad}")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                                append("Nombre: ")
                            }
                            withStyle(style = SpanStyle(color = Color.Green)) {
                                append("${prioridad.nombre}")
                            }
                        }
                    )
                }
                IconButton(
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        tint = Color.Red,
                        contentDescription = "Eliminar"
                    )
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            showDialog = false
                        },
                        icon = { Icon(Icons.Default.Warning, contentDescription = null) },
                        title = {
                            Text(text = "Eliminar Prioridad")
                        },
                        text = {
                            Text("¿Estás seguro de que quieres eliminar esta prioridad?  " +
                                    "                                                      Esta acción no se puede deshacer.")
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    onDeleteClick()
                                    showDialog = false
                                }
                            ) {
                                Text("Eliminar")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    showDialog = false
                                }
                            ) {
                                Text("Cancelar")
                            }
                        }
                    )
                }
            }

            if (isExpanded) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                            append("Decripción: ")
                        }
                        withStyle(style = SpanStyle(color = Color.Green)) {
                            append("${prioridad.descripcion}")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                            append("Plazo: ")
                        }
                        withStyle(style = SpanStyle(color = Color.Green)) {
                            append("${prioridad.plazo}")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                            append("Es Nulo: ")
                        }
                        withStyle(style = SpanStyle(color = Color.Green)) {
                            append("${prioridad.esNulo}")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                            append("Creado Por: ")
                        }
                        withStyle(style = SpanStyle(color = Color.Green)) {
                            append("${prioridad.Creador}")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                            append("Fecha de creación: ")
                        }
                        withStyle(style = SpanStyle(color = Color.Green)) {
                            append("${prioridad.fechaCreacion}")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                            append("Modificado Por: ")
                        }
                        withStyle(style = SpanStyle(color = Color.Green)) {
                            append("${prioridad.modidicador}")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)) {
                            append("Fecha de Modificación: ")
                        }
                        withStyle(style = SpanStyle(color = Color.Green)) {
                            append("${prioridad.fechaModificacion}")
                        }
                    }
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

@Composable
fun AlertDialogWithIconSample() {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
            title = {
                Text(text = "Title")
            },
            text = {
                Text(
                    "This area typically contains the supportive text " +
                            "which presents the details regarding the Dialog's purpose."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}


