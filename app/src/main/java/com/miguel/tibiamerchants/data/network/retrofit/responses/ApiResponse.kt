package com.miguel.tibiamerchants.data.network.retrofit.responses

data class ApiResponse <T>(
    val statusCode: Int,
    val body: T
)