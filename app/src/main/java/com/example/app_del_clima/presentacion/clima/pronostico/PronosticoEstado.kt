package com.example.app_del_clima.presentacion.clima.pronostico
import com.example.app_del_clima.repository.modelos.Clima
import com.example.app_del_clima.repository.modelos.ListForecast

sealed class PronosticoEstado {
    data class Exitoso (
        val climas: List<ListForecast>,
        ) : PronosticoEstado()
    data class Error(
        val mensaje :String = "",
    ) : PronosticoEstado()
    data object Vacio: PronosticoEstado()
    data object Cargando: PronosticoEstado()

}
