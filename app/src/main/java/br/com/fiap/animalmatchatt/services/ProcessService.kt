package br.com.fiap.animalmatchatt.services

import br.com.fiap.animalmatchatt.model.DaysUpdate
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProcessService {
    @POST("create/{id}")
    fun createProcess(@Path("id") id: String, @Header("Authorization") authToken: String): Call<Process>

    @GET("list-processes")
    fun getProcesses(): Call<List<Process>>

    @GET("list-process/adopter")
    fun getProcessesByAdopter(@Header("Authorization") authToken: String): Call<List<Process>>

    @GET("list-process/animal/{id}")
    fun getProcessByAnimal(@Path("id") id: String): Call<Process>

    @GET("list-process/owner")
    fun getAnimalsInAdoptionProcess(@Header("Authorization") authToken: String): Call<List<Process>>

    @PUT("update/{id}")
    fun updateProcess(@Path("id") id: String, @Header("Authorization") authToken: String, @Body process: Process): Call<Process>

    @PUT("cancel/{id}")
    fun cancelProcess(@Path("id") id: String, @Header("Authorization") authToken: String): Call<Process>

    @PUT("update-days/{id}")
    fun updateProcessDays(@Path("id") id: String, @Body daysUpdate: DaysUpdate): Call<Process>
}