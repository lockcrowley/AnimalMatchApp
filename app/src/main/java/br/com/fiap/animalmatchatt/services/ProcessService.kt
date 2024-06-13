package br.com.fiap.animalmatchatt.services

import br.com.fiap.animalmatchatt.model.DaysUpdate
import br.com.fiap.animalmatchatt.model.ProcessAd
import br.com.fiap.animalmatchatt.model.ProcessResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProcessService {
    @POST("process/create/{id}")
    fun createProcess(@Path("id") id: String ?, @Header("Authorization") token: String ?): Call<ProcessAd>

    @GET("process/list-processes")
    fun getProcesses(): Call<List<ProcessAd>>

    @GET("process/list-process/adopter")
    fun getProcessesByAdopter(@Header("Authorization") token: String ?): Call<ProcessResponse>

    @GET("process/list-process/animal/{id}")
    fun getProcessByAnimal(@Path("id") id: String): Call<ProcessAd>

    @GET("process/list-process/owner")
    fun getAnimalsInAdoptionProcess(@Header("Authorization") token: String ?): Call<ProcessResponse>

    @PUT("process/update/{id}")
    fun updateProcessToConcluded(
        @Path("id") id: String,
        @Header("Authorization") token: String ?
    ): Call<Void>

    @PUT("process/cancel/{id}")
    fun cancelProcess(
        @Path("id") id: String,
        @Header("Authorization") token: String ?
    ): Call<Void>

    @PUT("process/update-days/{id}")
    fun updateProcessDays(@Path("id") id: String, @Body daysUpdate: DaysUpdate): Call<ProcessAd>
}