package com.example.app_del_clima.presentacion.clima.actual

sealed class ClimaEstado {
    data class Exitoso(
        val ciudad: String = "",
        val temperatura: Double = 0.0,
        val descripcion: String= "",
        val st:Double = 0.0,
        val humedad: String="",
        ) : ClimaEstado()
    data class Error(
        val mensaje :String = "",
    ) : ClimaEstado()
    data object Vacio: ClimaEstado()
    data object Cargando: ClimaEstado()

}
