package br.com.fiap.animalmatchatt.services

import br.com.fiap.animalmatchatt.model.Animal
import br.com.fiap.animalmatchatt.model.AnimalResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimalService {

    @POST("animals/create")
    fun createAnimal(
        @Header("Authorization") token: String ?,
        @Body animal: Animal
    ): Call<Animal>

    @GET("animals/list-animals")
    fun getAllAnimals(): Call<List<Animal>>

    @GET("animals/list-animal/name")
    fun getAnimalByName(@Query("name") name: String): Call<Animal>

    @GET("animals/list-user-animals")
    fun getAnimalsByUser(@Header("Authorization") token: String ?): Call<AnimalResponse>

    @GET("animals/animals-to-adopte")
    fun getAnimalsToAdopt(@Header("Authorization") token: String ?): Call<AnimalResponse>

    @PUT("animals/edit-animal/{id}")
    fun editAnimal(
        @Header("Authorization") token: String ?,
        @Path("id") id: String ?,
        @Body animal: Animal
    ): Call<Animal>

    @DELETE("animals/delete/{id}")
    fun deleteAnimal(@Path("id") id: String): Call<Void>
}
