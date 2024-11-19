package com.example.app_del_clima.presentacion.ciudades

import com.example.app_del_clima.repository.modelos.Ciudad

sealed class CiudadesEstado {
    data object Vacio: CiudadesEstado()
    data object Cargando: CiudadesEstado()
    data class Resultado( val ciudades : List<Ciudad> ) : CiudadesEstado()
    data class Error(val mensaje: String): CiudadesEstado()
}