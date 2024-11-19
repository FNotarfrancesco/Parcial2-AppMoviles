package com.example.app_del_clima.repository

import com.example.app_del_clima.repository.modelos.Ciudad
import com.example.app_del_clima.repository.modelos.Clima
import com.example.app_del_clima.repository.modelos.ListForecast

interface Repositorio {
    suspend fun buscarCiudad(ciudad: String): List<Ciudad>
    suspend fun traerClima(lat: Float, lon: Float) : Clima
    suspend fun traerPronostico(nombre: String) : List<ListForecast>
}