package com.example.app_del_clima.presentacion.clima

import android.content.Context
import android.content.Intent
import com.example.app_del_clima.repository.modelos.Clima

class ShareManager(private val context: Context) {

    fun shareWeatherStats(weather: Clima) {
        val message = """
            Actualizacion clima 🌤️
            Ubicacion: ${weather.name}
            Temperatura: ${weather.main.temp}
            Descripcion: ${weather.weather[0].description}
        """.trimIndent()

        shareMessege(message)
    }

    private fun shareMessege(messege: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, messege)
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE, "Estado Clima")
        }
        val shareIntent = Intent.createChooser(intent, null)
        context.startActivity(shareIntent)
    }

}