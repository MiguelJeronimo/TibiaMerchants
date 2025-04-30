package com.miguel.tibiamerchants.data.network.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    /**
     * @param url -> url del endpoint a consumir
     * @return Regresa una instancia de retrofit
     * */
    fun getRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpConfigs())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun okHttpConfigs(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .build()
    }
}