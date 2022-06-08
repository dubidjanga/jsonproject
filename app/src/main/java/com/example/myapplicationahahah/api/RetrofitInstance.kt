package com.example.myapplicationahahah.api

import com.example.myapplicationahahah.util.Constants.Companion.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


object RetrofitInstance {

    private val retrofit by lazy {
        /*val client = OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .interceptors().add(object  : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    return onOnIntercept(chain)
                }
            })
            .build()
        client.connectTimeoutMillis()
        client.setConnectTimeout(10, TimeUnit.SECONDS)
        client.setReadTimeout(30, TimeUnit.SECONDS)
        client.interceptors.add(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                return onOnIntercept(chain)
            }
        }
        */
        Retrofit.Builder()
            .baseUrl(BASE_URL + "/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SimpleAPI by lazy{
        retrofit.create(SimpleAPI::class.java)

    }
}