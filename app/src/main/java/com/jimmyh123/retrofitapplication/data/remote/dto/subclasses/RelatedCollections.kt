package com.jimmyh123.retrofitapplication.data.remote.dto.subclasses

data class RelatedCollections(
    val results: List<Result>,
    val total: Int,
    val type: String
)