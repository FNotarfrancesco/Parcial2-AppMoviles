package com.example.app_del_clima.presentacion.ciudades

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.app_del_clima.repository.RepositorioApi
import com.example.app_del_clima.router.Enrutador
import com.example.app_del_clima.router.Router

@Composable
fun CiudadesPage(
    navHostController:  NavHostController
) {
    val viewModel : CiudadesViewModel = viewModel(
        factory = CiudadesViewModelFactory(
            repositorio = RepositorioApi(),
            router = Enrutador(navHostController)
        )
    )
    CiudadesView(
        state = viewModel.uiState,
        onAction = { intencion ->
            viewModel.ejecutar(intencion)
        }
    )
}
