package com.miguel.tibiamerchants.domain.models

import android.R
import com.google.gson.annotations.SerializedName

/**
 *
 * {
 *     "count": 1537,
 *     "ads": [
 *         {
 *             "id": 47937,
 *             "user_id": 6156,
 *             "type": 0,
 *             "item_id": 5249,
 *             "item_tier": 0,
 *             "currency_type": 1,
 *             "price": "0",
 *             "world_id": 98,
 *             "is_closed": 0,
 *             "is_rookgaard": false,
 *             "item_amount": 1,
 *             "house_id": null,
 *             "highlighted_until": null,
 *             "created_at": "2024-06-06T22:01:39.375Z",
 *             "item_name": "The Dragon Spirit",
 *             "item_look": "You see  the dragon spirit.\nIt weighs 26.02 oz.\nIt represents the spirit of your first dragon. Granted by Intibia.com",
 *             "world_name": "Obscubra",
 *             "world_pvp_type": "Retro Hardcore PvP",
 *             "world_battleye_color": "green",
 *             "username": "sirius",
 *             "is_whatsapp_verified": 1,
 *             "avatar": "uzgod",
 *             "tibia_id": null,
 *             "house_name": null,
 *             "size": null,
 *             "rent": null,
 *             "beds": null,
 *             "floors": null,
 *             "rooms": null,
 *             "windows": null,
 *             "town": null,
 *             "coordinates": null,
 *             "is_guildhall": false,
 *             "furnitures": null,
 *             "likes": "21",
 *             "is_user_verified": true,
 *             "converted_price": 0
 *         },
 * **/

data class TibiaTradeModel(
    val count: Int,
    val ads: ArrayList<Trade>,
    @SerializedName("highlighted_ads")
    val highlightedAds: ArrayList<Trade>,
)

data class Trade(
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
    val type: Int,
    @SerializedName("item_id")
    val itemId: Int? = null,
    @SerializedName("item_tier")
    val itemTier: Int,
    @SerializedName("currency_type")
    val currencyType: Int,
    @SerializedName("price")
    val price: Long,
    @SerializedName("world_id")
    val worldId: Int,
    @SerializedName("is_closed")
    val isClosed: Int,
    @SerializedName("is_rookgaard")
    val isRookgaard: Boolean,
    @SerializedName("item_amount")
    val itemAmount: Int,
    @SerializedName("house_id")
    val houseId: Int?=null,
    @SerializedName("highlighted_until")
    val highlightedUntil: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("item_name")
    val itemName: String? = null,
    @SerializedName("item_look")
    val itemLook: String? = null,
    @SerializedName("world_name")
    val worldName: String,
    @SerializedName("world_pvp_type")
    val worldPvpType: String,
    @SerializedName("world_battleye_color")
    val worldBattleyeColor: String,
    @SerializedName("username")
    val userName: String,
    @SerializedName("is_whatsapp_verified")
    val isWhatsappVerified: Int,
    val avatar: String,
    @SerializedName("tibia_id")
    val tibiaId: Int? = null,
    @SerializedName("house_name")
    val houseName: String? = null,
    val size: Int?=null,
    val rent: Long? = null,
    val beds: Int? = null,
    val floors: Int? = null,
    val rooms: Int? = null,
    val windows: Int? = null,
    val town: String? = null,
    val coordinates: String? = null,
    @SerializedName("is_guildhall")
    val isGuildhall: Boolean,
    val furnitures: String? = null,
    val likes: String,
    @SerializedName("is_user_verified")
    val isUserVerified: Boolean,
    @SerializedName("converted_price")
    val convertedPrice: Long? = null
)

//Tibia trade profile user
data class Profile(
    @SerializedName("created_at")
    val createdAt: String,
    val avatar: String,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    val ads: ArrayList<Trade>,
    val presentations: ArrayList<Any>?= null,
)

data class TibiaTradeItemModel(
    @SerializedName("is_closed")
    val isClosed: Boolean,
    @SerializedName("screenshot_count")
    val screenshotCount: Int? = null,
    @SerializedName("has_feed_image")
    val hasFeedImage: Boolean,
    @SerializedName("has_story_image")
    val hasStoryImage: Boolean,
    @SerializedName("has_feed_pt_br_image")
    val hasFeedPtBrImage: Boolean,
    @SerializedName("has_story_pt_br_image")
    val hasStoryPtBrImage: Boolean,
    @SerializedName("ad")
    val ad: Trade,
)