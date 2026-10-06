package com.miguel.tibiamerchants.domain.models

import com.miguel.tibiamerchants.data.network.retrofit.responses.TibiaTradeUserProfileModel

data class ProfileDto(
    val createdAt: String,
    val avatar: String,
    val isVerified: Boolean,
    val ads: List<TradeDto>? = null,
    val presentations: List<Any>? = null
)

data class TradeDto(
    val id: Int,
    val userId: Int,
    val type: Int,
    val itemId: Int? = null,
    val itemTier: Int = 0,
    val currencyType: Int = 0,
    val price: Long? = 0L,
    val worldId: Int = 0,
    val isClosed: Int? = null,
    val isRookgaard: Boolean = false,
    val imageUrl: String? = null,
    val itemAmount: Int = 1,
    val houseId: Int? = null,
    val highlightedUntil: String? = null,
    val createdAt: String? = null,
    val itemName: String? = null,
    val itemLook: String? = null,
    val worldName: String? = null,
    val worldPvpType: String? = null,
    val worldBattleyeColor: String? = null,
    val userName: String? = null,
    val isWhatsappVerified: Int? = null,
    val avatar: String? = null,
    val tibiaId: Int? = null,
    val houseName: String? = null,
    val size: Int? = null,
    val rent: Long? = null,
    val beds: Int? = null,
    val floors: Int? = null,
    val rooms: Int? = null,
    val windows: Int? = null,
    val town: String? = null,
    val coordinates: String? = null,
    val isGuildhall: Boolean = false,
    val furnitures: String? = null,
    val likes: String? = null,
    val isUserVerified: Boolean = false,
    val convertedPrice: Long? = null,
    val tibiaBlackjackUsername: String? = null,
    val viewCount: Int? = null,
    val autoRenew: Boolean? = null,
    val autoHighlight: Boolean? = null,
    val tibiaBlackjackUsernameAlt: String? = null,
    val highlightPrepaid: Boolean? = null
)



fun TibiaTradeUserProfileModel.toDomain(): ProfileDto {
    return ProfileDto(
        createdAt = this.createdAt,
        avatar = this.avatar,
        isVerified = this.verified,
        ads = this.ads.map { it.toDomain() },
        presentations = this.presets
    )
}

fun Trade.toDomain(): TradeDto{
    return TradeDto(
        id = this.id,
        userId = this.userId,
        type = this.type,
        itemId = this.itemId,
        itemTier = this.itemTier,
        currencyType = this.currencyType,
        price = this.price,
        worldId = this.worldId,
        isClosed = this.isClosed,
        isRookgaard = this.isRookgaard,
        imageUrl = this.imageUrl,
        itemAmount = this.itemAmount,
        houseId = this.houseId,
        highlightedUntil = this.highlightedUntil,
        createdAt = this.createdAt,
        itemName = this.itemName,
        itemLook = this.itemLook,
        worldName = this.worldName,
        worldPvpType = this.worldPvpType,
        worldBattleyeColor = this.worldBattleyeColor,
        userName = this.userName,
        isWhatsappVerified = this.isWhatsappVerified,
        avatar = this.avatar,
        tibiaId = this.tibiaId,
        houseName = this.houseName,
        size = this.size,
        rent = this.rent,
        beds = this.beds,
        floors = this.floors,
        rooms = this.rooms,
        windows = this.windows,
        town = this.town,
        coordinates = this.coordinates,
        isGuildhall = this.isGuildhall,
        furnitures = this.furnitures,
        likes = this.likes,
        isUserVerified = this.isUserVerified,
        convertedPrice = this.convertedPrice,
        tibiaBlackjackUsername = this.tibiaBlackjackUsername,
        viewCount = this.viewCount,
        autoRenew = this.autoRenew,
        autoHighlight = this.autoHighlight,
        tibiaBlackjackUsernameAlt = this.tibiaBlackjackUsernameAlt,
        highlightPrepaid = this.highlightPrepaid
    )
}