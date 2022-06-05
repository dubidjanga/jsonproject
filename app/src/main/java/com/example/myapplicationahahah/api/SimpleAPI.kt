package com.example.myapplicationahahah.api
import com.example.myapplicationahahah.model.Blockchain
import retrofit2.Response
import retrofit2.http.GET

interface SimpleAPI {
    @GET("chain")
    suspend fun getChain(): Response<Blockchain>
}