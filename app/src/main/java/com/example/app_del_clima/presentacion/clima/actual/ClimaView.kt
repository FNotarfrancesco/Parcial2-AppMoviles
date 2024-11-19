package com.example.app_del_clima.presentacion.clima.actual

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.app_del_clima.presentacion.clima.ShareManager
import com.example.app_del_clima.theme.appdelclimaTheme


@Composable
fun ClimaView(
    modifier: Modifier = Modifier,
    state : ClimaEstado,
    onAction: (ClimaIntencion)->Unit
) {
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        onAction(ClimaIntencion.actualizarClima)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(state){
            is ClimaEstado.Error -> ErrorView(mensaje = state.mensaje)
            is ClimaEstado.Exitoso -> ClimaView(
                ciudad = state.ciudad,
                temperatura = state.temperatura,
                descripcion = state.descripcion,
                st = state.st,
                humedad = state.humedad,
            )
            ClimaEstado.Vacio -> LoadingView()
            ClimaEstado.Cargando -> EmptyView()
        }
        Spacer(modifier = Modifier.height(100.dp))
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
fun ClimaView(
    ciudad: String,
    temperatura: Double,
    descripcion: String,
    st: Double,
    humedad: String,

){
    Column {
        Text(text = ciudad, style = MaterialTheme.typography.titleLarge)
        Text(text = "${temperatura}°", style = MaterialTheme.typography.titleLarge)
        Text(text = descripcion, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Sensacion Termica: ${st}°", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Humedad: ${humedad}%", style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun ClimaPreviewVacio() {
    appdelclimaTheme {
        ClimaView(state = ClimaEstado.Vacio, onAction = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ClimaPreviewError() {
    appdelclimaTheme {
        ClimaView(state = ClimaEstado.Error("Se rompio todo"), onAction = {})
    }
}





