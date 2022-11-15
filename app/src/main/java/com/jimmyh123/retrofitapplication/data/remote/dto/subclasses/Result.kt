package com.jimmyh123.retrofitapplication.data.remote.dto.subclasses

data class Result(
    val cover_photo: CoverPhoto,
    val curated: Boolean,
    val description: String,
    val featured: Boolean,
    val id: String,
    val last_collected_at: String,
    val links: Links,
    val preview_photos: List<PreviewPhoto>,
    val `private`: Boolean,
    val published_at: String,
    val share_key: String,
    val tags: List<Tag>,
    val title: String,
    val total_photos: Int,
    val updated_at: String,
    val user: User
)