package com.ucne.prioridadesroom.ui.Prioridad

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.prioridadesroom.data.dao.PrioridadDao
import com.ucne.prioridadesroom.data.entity.PrioridadEntity
import com.ucne.prioridadesroom.data.repository.PrioridadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrioridadViewModel @Inject constructor(
    private val prioridadRepository: PrioridadRepository
) : ViewModel() {
    private val _state = MutableStateFlow(PrioridadState())
    val state = _state.asStateFlow()
    val prioridades: Flow<List<PrioridadEntity>> = prioridadRepository.getPrioridad()

    fun onEvent(event: PrioridadEvent) {
        when (event) {
            is PrioridadEvent.IdPrioridad -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(
                            idPrioridad = event.idPrioridad.toIntOrNull() ?: 0
                        )
                    )
                }
            }

            is PrioridadEvent.Nombre -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(nombre = event.nombre)
                    )
                }
            }

            is PrioridadEvent.Descripcion -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(descripcion = event.descripcion)
                    )
                }
            }

            is PrioridadEvent.Plazo -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(plazo = event.plazo.toIntOrNull() ?: 0)
                    )
                }
            }

            is PrioridadEvent.IsNull -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(esNulo = event.esNulo.toBoolean())
                    )
                }
            }

            is PrioridadEvent.CreadoPor -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(
                            Creador = event.creadoPor.toIntOrNull() ?: 0
                        )
                    )
                }
            }

            is PrioridadEvent.FechaCreacion -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(fechaCreacion = event.fechaCreacion)
                    )
                }
            }

            is PrioridadEvent.ModificadoPor -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(
                            modidicador = event.modificadoPor.toIntOrNull() ?: 0
                        )
                    )
                }
            }

            is PrioridadEvent.FechaModificaion -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(fechaModificacion = event.fechaModificaion)
                    )
                }
            }

            PrioridadEvent.onSave -> {
                val nombre = state.value.prioridades.nombre
                val descripcion = state.value.prioridades.descripcion
                val plazo = state.value.prioridades.plazo
                val esNulo = state.value.prioridades.esNulo
                val creadoPor = state.value.prioridades.Creador
                val fechaCreacion = state.value.prioridades.fechaCreacion
                val modificadoPor = state.value.prioridades.modidicador
                val fechaModificaion = state.value.prioridades.fechaModificacion

                if (nombre.isBlank() || descripcion.isBlank() || plazo == 0  || esNulo == null ||
                    creadoPor == 0 || fechaCreacion.isBlank() || modificadoPor == 0 || fechaModificaion.isBlank()) {
                    _state.update {
                        it.copy(
                            error = "Por favor, complete todos los campos."
                        )
                    }
                    return
                }

                val prioridad = PrioridadEntity(
                    nombre = nombre,
                    descripcion = descripcion,
                    plazo = plazo,
                    esNulo = esNulo,
                    Creador = creadoPor,
                    fechaCreacion = fechaCreacion,
                    modidicador = modificadoPor,
                    fechaModificacion = fechaModificaion,
                )

                _state.update {
                    it.copy(
                        isLoading = true,
                        error = null,
                        succesMessage = null
                    )
                }

                viewModelScope.launch {
                    try {
                        prioridadRepository.upsert(prioridad)
                        _state.update {
                            it.copy(
                                isLoading = false,
                                succesMessage = "Se guardó correctamente"
                            )
                        }
                    } catch (e: Exception) {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = "Error al guardar: ${e.message}"
                            )
                        }
                    }
                }

                _state.update {
                    it.copy(
                        prioridades = PrioridadEntity()
                    )
                }
            }

            PrioridadEvent.onNew -> {
                _state.update {
                    it.copy(
                        succesMessage = null,
                        error = null,
                        prioridades = PrioridadEntity()
                    )
                }
            }

            is PrioridadEvent.onDelete -> {
                viewModelScope.launch {
                    prioridadRepository.delete(event.prioridad)
                }
            }
        }
    }
}

data class PrioridadState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val prioridades: PrioridadEntity = PrioridadEntity(),
    val succesMessage: String? = null,
)

sealed interface PrioridadEvent {
    data class IdPrioridad(val idPrioridad: String) : PrioridadEvent
    data class Nombre(val nombre: String) : PrioridadEvent
    data class Descripcion(val descripcion: String) : PrioridadEvent
    data class Plazo(val plazo: String) : PrioridadEvent
    data class IsNull(val esNulo: String) : PrioridadEvent
    data class CreadoPor(val creadoPor: String) : PrioridadEvent
    data class FechaCreacion(val fechaCreacion: String) : PrioridadEvent
    data class ModificadoPor(val modificadoPor: String) : PrioridadEvent
    data class FechaModificaion(val fechaModificaion: String) : PrioridadEvent
    data class onDelete(val prioridad: PrioridadEntity) : PrioridadEvent
    object onSave : PrioridadEvent
    object onNew : PrioridadEvent
}