package com.miguel.tibiamerchants.data.network.retrofit.responses

import com.google.gson.annotations.SerializedName

data class TibiaTradeItemProfileModel(
    @SerializedName("info") val info: Info,
    @SerializedName("marketPrice")val marketPrice: MarketPriceModel? = null,
    @SerializedName("hasFeedImage") val hasFeedImage: Boolean,
    @SerializedName("hasStoryImage") val hasStoryImage: Boolean,
    @SerializedName("hasFeedPtBrImage") val hasFeedPtBrImage: Boolean,
    @SerializedName("hasStoryPtBrImage") val hasStoryPtBrImage: Boolean,
    @SerializedName("screenshotCount") val screenshotCount: Int? = null,
    @SerializedName("closed") val closed: Boolean,
    @SerializedName("active") val active: Boolean
)

data class MarketPriceModel(
    @SerializedName("referencePrice") val referencePrice: Int? = null,
    @SerializedName("sampleSize") val sampleSize: Int? = null,
    @SerializedName("source") val source: String? = null,
)

data class Info(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("itemName") val itemName: String? = null,
    @SerializedName("imageUrl") val imageUrl: String? = null,
    @SerializedName("itemAmount") val itemAmount: Int? = null,
    @SerializedName("itemId") val itemId: Int? = null,
    @SerializedName("tibiaId") val tibiaId: Int? = null,
    @SerializedName("houseId") val houseId: Int? = null,
    @SerializedName("houseName") val houseName: String? = null,
    @SerializedName("itemTier") val itemTier: Int? = null,
    @SerializedName("itemLook") val itemLook: String? = null,
    @SerializedName("username") val username: String? = null,
    @SerializedName("userId") val userId: Int? = null,
    @SerializedName("price") val price: Long? = null,
    @SerializedName("currencyType") val currencyType: Int? = null,
    @SerializedName("roomsImages") val roomsImages: List<String>? = null,
    @SerializedName("type") val type: Int? = null,
    @SerializedName("worldId") val worldId: Int? = null,
    @SerializedName("worldName") val worldName: String? = null,
    @SerializedName("worldPvpType") val worldPvpType: String? = null,
    @SerializedName("worldBattleyeColor") val worldBattleEyeColor: String? = null,
    @SerializedName("createdAt") val createdAt: String? = null,
    @SerializedName("viewCount") val viewCount: Int? = null,
    @SerializedName("autoRenew") val autoRenew: Boolean,
    @SerializedName("autoHighlight") val autoHighlight: Boolean,
    @SerializedName("town") val town: String? = null,
    @SerializedName("size") val size: Int? = null,
    @SerializedName("rooms") val rooms: Int? = null,
    @SerializedName("beds") val beds: Int? = null,
    @SerializedName("floors") val floors: Int? = null,
    @SerializedName("rent") val rent: Int? = null,
    @SerializedName("windows") val windows: Int? = null,
    @SerializedName("furnitures") val furnitures: String? = null,
    @SerializedName("coordinates") val coordinates: String? = null,
    @SerializedName("convertedPrice") val convertedPrice: Long? = null,
    @SerializedName("avatar") val avatar: String? = null,
    @SerializedName("tibiaBlackjackUsername") val tibiaBlackjackUsername: String? = null,
    @SerializedName("rookgaard") val rookgaard : Boolean,
    @SerializedName("guildhall") val guildhall: Boolean,
    @SerializedName("userVerified") val userVerified: Boolean,
    @SerializedName("highlightPrepaid") val highlightPrepaid: Boolean
)