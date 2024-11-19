package com.example.app_del_clima.router

class MockRouter: Router {
    override fun navegar(ruta: Ruta) {
        println("navegar a : ${ ruta.id }" )
    }
}