package com.example.dogimagegenerator.model.api



import com.example.dogimagegenerator.model.dataClass.DogResponse
import retrofit2.http.GET

interface ApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): DogResponse
}
