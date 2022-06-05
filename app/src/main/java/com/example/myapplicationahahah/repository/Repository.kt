package com.example.myapplicationahahah.repository

import com.example.myapplicationahahah.api.RetrofitInstance
import com.example.myapplicationahahah.model.Blockchain
import retrofit2.Response

class Repository {
    suspend fun getChain():Response<Blockchain> {
        return RetrofitInstance.api.getChain()
    }
}