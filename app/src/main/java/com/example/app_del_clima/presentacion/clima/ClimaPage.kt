package com.example.app_del_clima.presentacion.clima

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.app_del_clima.presentacion.clima.actual.ClimaView
import com.example.app_del_clima.presentacion.clima.actual.ClimaViewModel
import com.example.app_del_clima.presentacion.clima.actual.ClimaViewModelFactory
import com.example.app_del_clima.presentacion.clima.pronostico.PronosticoView
import com.example.app_del_clima.presentacion.clima.pronostico.PronosticoViewModel
import com.example.app_del_clima.presentacion.clima.pronostico.PronosticoViewModelFactory
import com.example.app_del_clima.repository.RepositorioApi
import com.example.app_del_clima.router.Enrutador
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import com.example.app_del_clima.repository.modelos.Clima
import com.example.app_del_clima.theme.appdelclimaTheme

@Composable
fun ClimaPage(
    navHostController: NavHostController,
    lat: Float,
    lon: Float,
    nombre: String
) {
    val viewModel: ClimaViewModel = viewModel(
        factory = ClimaViewModelFactory(
            repositorio = RepositorioApi(),
            router = Enrutador(navHostController),
            lat = lat,
            lon = lon,
            nombre = nombre
        )
    )
    val pronosticoViewModel: PronosticoViewModel = viewModel(
        factory = PronosticoViewModelFactory(
            repositorio = RepositorioApi(),
            router = Enrutador(navHostController),
            nombre = nombre
        )
    )


    Column {
        ClimaView(
            state = viewModel.uiState,
            onAction = { intencion ->
                viewModel.ejecutar(intencion)
            }
        )
        PronosticoView(
            state = pronosticoViewModel.uiState,
            onAction = { intencion ->
                pronosticoViewModel.ejecutar(intencion)
            }
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> PageView(model: T? = null ,content: @Composable (Modifier) -> Unit) {
                        if(model != null && model is Clima) {
                            CreateShareButton(model)
                        }
                        CreateButton(action = {  }) {
                            Text(text = "Compartir Clima")
                        }
                    }


@Composable
private fun CreateButton(action: () -> Unit, content: @Composable () -> Unit) {
    Button(
        onClick = {action()},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .padding(end = 8.dp)
    )
    { content() }
}

@Composable
private fun CreateShareButton(weather: Clima) {
    val context = LocalContext.current
    val shareManager = ShareManager(context)
    CreateButton(action = { shareManager.shareWeatherStats(weather) }) {
    }
}

@Preview(showBackground = true)
@Composable
fun PageViewPreview() {
    appdelclimaTheme() {
        PageView<Clima> {
            Text("Todo mal")
        }
    }
}





