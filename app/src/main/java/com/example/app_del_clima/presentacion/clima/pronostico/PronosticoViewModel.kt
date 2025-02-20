package com.example.app_del_clima.presentacion.clima.pronostico
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.app_del_clima.repository.Repositorio
import com.example.app_del_clima.router.Router
import kotlinx.coroutines.launch

class PronosticoViewModel(
    val respositorio: Repositorio,
    val router: Router,
    val nombre: String
) : ViewModel() {

    var uiState by mutableStateOf<PronosticoEstado>(PronosticoEstado.Vacio)

    fun ejecutar(intencion: PronosticoIntencion){
        when(intencion){
            PronosticoIntencion.actualizarClima -> traerPronostico()
        }
    }

    fun traerPronostico() {
        uiState = PronosticoEstado.Cargando
        viewModelScope.launch {
            try{
                val forecast = respositorio.traerPronostico(nombre).filter {
                    //TODO agregar logica de filtrado
                    true
                }
                uiState = PronosticoEstado.Exitoso(forecast)
            } catch (exception: Exception){
                uiState = PronosticoEstado.Error(exception.localizedMessage ?: "Error desconocido")
            }
        }
    }

}

class PronosticoViewModelFactory(
    private val repositorio: Repositorio,
    private val router: Router,
    private val nombre: String,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PronosticoViewModel::class.java)) {
            return PronosticoViewModel(repositorio,router,nombre) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}