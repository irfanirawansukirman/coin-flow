package com.irfanirawansukirman.composecrypto.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//data class CoinsDto(
//    @SerializedName("id")
//    val id: String?,
//    @SerializedName("is_active")
//    val isActive: Boolean?,
//    @SerializedName("is_new")
//    val isNew: Boolean?,
//    @SerializedName("name")
//    val name: String?,
//    @SerializedName("rank")
//    val rank: Int?,
//    @SerializedName("symbol")
//    val symbol: String?,
//    @SerializedName("type")
//    val type: String?
//)

@JsonClass(generateAdapter = true)
data class CoinsDto(
    @Json(name = "id")
    val id: String?,
    @Json(name = "is_active")
    val isActive: Boolean?,
    @Json(name = "is_new")
    val isNew: Boolean?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "rank")
    val rank: Int?,
    @Json(name = "symbol")
    val symbol: String?,
    @Json(name = "type")
    val type: String?
)