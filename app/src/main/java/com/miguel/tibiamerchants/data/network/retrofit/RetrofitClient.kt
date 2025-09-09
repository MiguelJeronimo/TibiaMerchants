package com.miguel.tibiamerchants.data.network.retrofit

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    /**
     * @param url -> url del endpoint a consumir
     * @return Regresa una instancia de retrofit
     * */
    fun getRetrofit(url: String, token: String? = null): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpConfigs(token = token))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun okHttpConfigs(token: String?): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(AuthInterceptor(token))
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .build()
    }
}