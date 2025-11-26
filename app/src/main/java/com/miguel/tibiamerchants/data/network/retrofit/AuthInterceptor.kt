package com.miguel.tibiamerchants.data.network.retrofit

import android.os.Build
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val token: String?): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
        if (!token.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }
        //device information can be added here as well
        requestBuilder.addHeader("Device-Brand", Build.BRAND)
        requestBuilder.addHeader("Device-Model", Build.MODEL)
        requestBuilder.addHeader("OS-Version", Build.VERSION.RELEASE)
        requestBuilder.addHeader("Device-Manufacturer", Build.MANUFACTURER)
        requestBuilder.addHeader("SDK", Build.VERSION.SDK_INT.toString())
        Log.i("DeviceInfo", "Brand: ${Build.BRAND}, Model: ${Build.MODEL}, OS Version: ${Build.VERSION.RELEASE}, Manufacturer: ${Build.MANUFACTURER}, SDK: ${Build.VERSION.SDK_INT}")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}