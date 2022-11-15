package com.jimmyh123.retrofitapplication.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.jimmyh123.retrofitapplication.data.remote.dto.subclasses.*
import com.jimmyh123.retrofitapplication.domain.model.Unsplash

data class UnsplashDto(
    @SerializedName("alt_description")
    val altDescription: String?,
    @SerializedName("blur_hash")
    val blurHash: String,
    val color: String,
    @SerializedName("created_at")
    val createdAt: String,
//    @SerializedName("current_user_collections")
//    val currentUserCollections: List<Any>,
    val description: String?,
    val height: Int,
    val id: String,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean?,
    val likes: Int,
    val links: Links,
    @SerializedName("promoted_at")
    val promotedAt: Any,
    val sponsorship: Sponsorship,
    @SerializedName("topic_submissions")
    val topicSubmissions: TopicSubmissions,
    @SerializedName("updated_at")
    val updatedAt: String,
    val urls: Urls?,
    val user: User?,
    val width: Int
)

fun UnsplashDto.toUnsplash(): Unsplash {
    return Unsplash(
        id = id,
        likes = likes,
        likedByUser = likedByUser,
        urls = urls,
        user = user,
    )
}