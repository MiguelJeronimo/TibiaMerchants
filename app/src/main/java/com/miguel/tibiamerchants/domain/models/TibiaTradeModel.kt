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
    @SerializedName("items")
    val ads: List<Trade>,
    @SerializedName("highlighted")
    val highlightedAds: List<Trade>,
)

data class Trade(
    val id: Int,
    @SerializedName(value = "userId", alternate = ["user_id"])
    val userId: Int,
    val type: Int,
    @SerializedName(value = "itemId", alternate = ["item_id"])
    val itemId: Int? = null,
    @SerializedName(value = "itemTier", alternate = ["item_tier"])
    val itemTier: Int = 0,
    @SerializedName(value = "currencyType", alternate = ["currency_type"])
    val currencyType: Int = 0,
    @SerializedName("price")
    val price: Long? = 0L,
    @SerializedName(value = "worldId", alternate = ["world_id"])
    val worldId: Int = 0,
    @SerializedName(value = "isClosed", alternate = ["is_closed"])
    val isClosed: Int? = null,
    @SerializedName(value = "rookgaard", alternate = ["is_rookgaard"])
    val isRookgaard: Boolean = false,
    @SerializedName(value = "itemAmount", alternate = ["item_amount"])
    val itemAmount: Int = 1,
    @SerializedName(value = "houseId", alternate = ["house_id"])
    val houseId: Int? = null,
    @SerializedName(value = "highlightedUntil", alternate = ["highlighted_until"])
    val highlightedUntil: String? = null,
    @SerializedName(value = "createdAt", alternate = ["created_at"])
    val createdAt: String? = null,
    @SerializedName(value = "itemName", alternate = ["item_name"])
    val itemName: String? = null,
    @SerializedName(value = "itemLook", alternate = ["item_look"])
    val itemLook: String? = null,
    @SerializedName(value = "worldName", alternate = ["world_name"])
    val worldName: String? = null,
    @SerializedName(value = "worldPvpType", alternate = ["world_pvp_type"])
    val worldPvpType: String? = null,
    @SerializedName(value = "worldBattleyeColor", alternate = ["world_battleye_color"])
    val worldBattleyeColor: String? = null,
    @SerializedName("username")
    val userName: String? = null,
    @SerializedName(value = "imageUrl", alternate = ["image_url"])
    val imageUrl: String? = null,
    @SerializedName(value = "isWhatsappVerified", alternate = ["is_whatsapp_verified"])
    val isWhatsappVerified: Int? = null,
    @SerializedName("avatar")
    val avatar: String? = null,
    @SerializedName(value = "tibiaId", alternate = ["tibia_id"])
    val tibiaId: Int? = null,
    @SerializedName(value = "houseName", alternate = ["house_name"])
    val houseName: String? = null,
    @SerializedName("size")
    val size: Int? = null,
    @SerializedName("rent")
    val rent: Long? = null,
    @SerializedName("beds")
    val beds: Int? = null,
    @SerializedName("floors")
    val floors: Int? = null,
    @SerializedName("rooms")
    val rooms: Int? = null,
    @SerializedName("windows")
    val windows: Int? = null,
    @SerializedName("town")
    val town: String? = null,
    @SerializedName("coordinates")
    val coordinates: String? = null,
    @SerializedName(value = "guildhall", alternate = ["is_guildhall"])
    val isGuildhall: Boolean = false,
    @SerializedName("furnitures")
    val furnitures: String? = null,
    @SerializedName("likes")
    val likes: String? = null,
    @SerializedName(value = "userVerified", alternate = ["is_user_verified"])
    val isUserVerified: Boolean = false,
    @SerializedName(value = "convertedPrice", alternate = ["converted_price"])
    val convertedPrice: Long? = null,
    @SerializedName("tibiaBlackjackUsername")
    val tibiaBlackjackUsername: String? = null,
    @SerializedName("viewCount")
    val viewCount: Int? = null,
    @SerializedName("autoRenew")
    val autoRenew: Boolean? = null,
    @SerializedName("autoHighlight")
    val autoHighlight: Boolean? = null,
    @SerializedName("tibiaBlackjackUsernameAlt")
    val tibiaBlackjackUsernameAlt: String? = null,
    @SerializedName("highlightPrepaid")
    val highlightPrepaid: Boolean? = null
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
