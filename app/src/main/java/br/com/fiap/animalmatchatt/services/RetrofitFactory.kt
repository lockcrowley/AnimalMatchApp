package br.com.fiap.animalmatchatt.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    //Alterar para o IP da maquina de quem estiver ligando o projeto
    //Exemplo: "http://192.168.1.1:3333/api/"
    private val URL = "http://SEUIP:SUAPORTA/api/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(service: Class<T>): T {
        return retrofitFactory.create(service)
    }
}