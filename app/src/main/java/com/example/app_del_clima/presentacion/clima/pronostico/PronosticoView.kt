package com.example.app_del_clima.presentacion.clima.pronostico

import com.example.app_del_clima.repository.modelos.Clima
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.app_del_clima.repository.modelos.ListForecast


@Composable
fun PronosticoView(
    modifier: Modifier = Modifier,
    state : PronosticoEstado,
    onAction: (PronosticoIntencion)->Unit
) {
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        onAction(PronosticoIntencion.actualizarClima)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(state){
            is PronosticoEstado.Error -> ErrorView(mensaje = state.mensaje)
            is PronosticoEstado.Exitoso -> PronosticoView(state.climas)
            PronosticoEstado.Vacio -> LoadingView()
            PronosticoEstado.Cargando -> EmptyView()
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun EmptyView(){
    Text(text = "No hay informacion.")
}

@Composable
fun LoadingView(){
    Text(text = "Cargando...")
}

@Composable
fun ErrorView(mensaje: String){
    Text(text = mensaje)
}

@Composable
fun PronosticoView(climas: List<ListForecast>) {
    Text(text = "Pronostico proximos 7 dias:", style = MaterialTheme.typography.titleMedium)
    Spacer(modifier = Modifier.height(20.dp))
    LazyColumn {
        items(items = climas.take(7)) {// Solo toma los primeros 7 elementos
            Card {
                Text(text = "${it.main.temp}")
            }
        }
    }
}
