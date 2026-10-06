package com.miguel.tibiamerchants.data.network.retrofit.responses

import com.google.gson.annotations.SerializedName
import com.miguel.tibiamerchants.domain.models.Trade

data class TibiaTradeUserProfileModel(
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("lastLogin") val lastLogin: String? = null,
    @SerializedName("avatar") val avatar: String,
    @SerializedName("concludedDealsCount") val concludedDealsCount: Int,
    @SerializedName("tibiaBlackjackUsername") val tibiaBlackjackUsername: String? = null,
    @SerializedName("ads") val ads: List<Trade>,
    @SerializedName("presets") val presets: List<Any>? = null,
    @SerializedName("verified") val verified: Boolean
)
