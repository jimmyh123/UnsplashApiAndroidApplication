package com.jimmyh123.retrofitapplication.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.jimmyh123.retrofitapplication.data.remote.dto.subclasses.*
import com.jimmyh123.retrofitapplication.domain.model.UnsplashDetail

data class UnsplashDetailDto(
    @SerializedName("alt_description")
    val altDescription: String?,
    @SerializedName("blur_hash")
    val blurHash: String?,
    val color: String?,
    @SerializedName("created_at")
    val createdAt: String?,
//    @SerializedName("current_user_collections")
//    val currentUserCollections: List<Any?>,
    val description: String?,
    val downloads: Int?,
    val exif: Exif?,
    val height: Int?,
    val id: String,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,
    val likes: Int,
    val links: Links?,
    val location: Location?,
    val meta: Meta?,
//    @SerializedName("promoted_at")
//    val promotedAt: Any?,
    @SerializedName("public_domain")
    val publicDomain: Boolean?,
    @SerializedName("related_collections")
    val relatedCollections: RelatedCollections?,
    val sponsorship: Sponsorship?,
    val tags: List<Tag>,
    @SerializedName("tags_preview")
    val tagsPreview: List<TagsPreview?>,
//    @SerializedName("topic_submissions")
//    val topics: List<Any?>,
    @SerializedName("updated_at")
    val updatedAt: String?,
    val urls: Urls?,
    val user: User?,
    val views: Int?,
    val width: Int?
)

fun UnsplashDetailDto.toUnsplashDetail(): UnsplashDetail {
    return UnsplashDetail(
        id = id,
        altDescription = altDescription,
        createdAt = createdAt,
        description = description,
        downloads = downloads,
        likes = likes,
        links = links,
        likedByUser = likedByUser,
        tags = tags,
        updatedAt = updatedAt,
        urls = urls,
        user = user,
        views = views
    )
}