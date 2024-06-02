package br.com.fiap.animalmatchatt.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val URL = "http://192.168.15.36:3333/api/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(service: Class<T>): T {
        return retrofitFactory.create(service)
    }
}