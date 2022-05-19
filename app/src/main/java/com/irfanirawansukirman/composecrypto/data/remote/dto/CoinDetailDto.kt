package com.irfanirawansukirman.composecrypto.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//data class CoinDetailDto(
//    @SerializedName("description")
//    val description: String?,
//    @SerializedName("development_status")
//    val developmentStatus: String?,
//    @SerializedName("first_data_at")
//    val firstDataAt: String?,
//    @SerializedName("hardware_wallet")
//    val hardwareWallet: Boolean?,
//    @SerializedName("hash_algorithm")
//    val hashAlgorithm: String?,
//    @SerializedName("id")
//    val id: String?,
//    @SerializedName("is_active")
//    val isActive: Boolean?,
//    @SerializedName("is_new")
//    val isNew: Boolean?,
//    @SerializedName("last_data_at")
//    val lastDataAt: String?,
//    @SerializedName("links")
//    val links: Links?,
//    @SerializedName("links_extended")
//    val linksExtended: List<LinksExtended>?,
//    @SerializedName("message")
//    val message: String?,
//    @SerializedName("name")
//    val name: String?,
//    @SerializedName("open_source")
//    val openSource: Boolean?,
//    @SerializedName("org_structure")
//    val orgStructure: String?,
//    @SerializedName("proof_type")
//    val proofType: String?,
//    @SerializedName("rank")
//    val rank: Int?,
//    @SerializedName("started_at")
//    val startedAt: String?,
//    @SerializedName("symbol")
//    val symbol: String?,
//    @SerializedName("tags")
//    val tags: List<Tag>?,
//    @SerializedName("team")
//    val teamMember: List<TeamMember>?,
//    @SerializedName("type")
//    val type: String?,
//    @SerializedName("whitepaper")
//    val whitepaper: Whitepaper?
//)
//
//data class Links(
//    @SerializedName("explorer")
//    val explorer: List<String>?,
//    @SerializedName("facebook")
//    val facebook: List<String>?,
//    @SerializedName("reddit")
//    val reddit: List<String>?,
//    @SerializedName("source_code")
//    val sourceCode: List<String>?,
//    @SerializedName("website")
//    val website: List<String>?,
//    @SerializedName("youtube")
//    val youtube: List<String>?
//)
//
//data class LinksExtended(
//    @SerializedName("stats")
//    val stats: Stats?,
//    @SerializedName("type")
//    val type: String?,
//    @SerializedName("url")
//    val url: String?
//)
//
//data class Stats(
//    @SerializedName("contributors")
//    val contributors: Int?,
//    @SerializedName("followers")
//    val followers: Int?,
//    @SerializedName("stars")
//    val stars: Int?,
//    @SerializedName("subscribers")
//    val subscribers: Int?
//)
//
//data class Tag(
//    @SerializedName("coin_counter")
//    val coinCounter: Int?,
//    @SerializedName("ico_counter")
//    val icoCounter: Int?,
//    @SerializedName("id")
//    val id: String?,
//    @SerializedName("name")
//    val name: String?
//)
//
//data class TeamMember(
//    @SerializedName("id")
//    val id: String?,
//    @SerializedName("name")
//    val name: String?,
//    @SerializedName("position")
//    val position: String?
//)
//
//data class Whitepaper(
//    @SerializedName("link")
//    val link: String?,
//    @SerializedName("thumbnail")
//    val thumbnail: String?
//)

@JsonClass(generateAdapter = true)
data class CoinDetailDto(
    @Json(name = "description")
    val description: String?,
    @Json(name = "development_status")
    val developmentStatus: String?,
    @Json(name = "first_data_at")
    val firstDataAt: String?,
    @Json(name = "hardware_wallet")
    val hardwareWallet: Boolean?,
    @Json(name = "hash_algorithm")
    val hashAlgorithm: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "is_active")
    val isActive: Boolean?,
    @Json(name = "is_new")
    val isNew: Boolean?,
    @Json(name = "last_data_at")
    val lastDataAt: String?,
    @Json(name = "links")
    val links: Links?,
    @Json(name = "links_extended")
    val linksExtended: List<LinksExtended>?,
    @Json(name = "message")
    val message: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "open_source")
    val openSource: Boolean?,
    @Json(name = "org_structure")
    val orgStructure: String?,
    @Json(name = "proof_type")
    val proofType: String?,
    @Json(name = "rank")
    val rank: Int?,
    @Json(name = "started_at")
    val startedAt: String?,
    @Json(name = "symbol")
    val symbol: String?,
    @Json(name = "tags")
    val tags: List<Tag>?,
    @Json(name = "team")
    val teamMember: List<TeamMember>?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "whitepaper")
    val whitepaper: Whitepaper?
)

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "explorer")
    val explorer: List<String>?,
    @Json(name = "facebook")
    val facebook: List<String>?,
    @Json(name = "reddit")
    val reddit: List<String>?,
    @Json(name = "source_code")
    val sourceCode: List<String>?,
    @Json(name = "website")
    val website: List<String>?,
    @Json(name = "youtube")
    val youtube: List<String>?
)

@JsonClass(generateAdapter = true)
data class LinksExtended(
    @Json(name = "stats")
    val stats: Stats?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class Stats(
    @Json(name = "contributors")
    val contributors: Int?,
    @Json(name = "followers")
    val followers: Int?,
    @Json(name = "stars")
    val stars: Int?,
    @Json(name = "subscribers")
    val subscribers: Int?
)

@JsonClass(generateAdapter = true)
data class Tag(
    @Json(name = "coin_counter")
    val coinCounter: Int?,
    @Json(name = "ico_counter")
    val icoCounter: Int?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?
)

@JsonClass(generateAdapter = true)
data class TeamMember(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "position")
    val position: String?
)

@JsonClass(generateAdapter = true)
data class Whitepaper(
    @Json(name = "link")
    val link: String?,
    @Json(name = "thumbnail")
    val thumbnail: String?
)