package com.example.jobfinder.data.remote


import com.google.gson.annotations.SerializedName

data class EmployerReview(
    @SerializedName("employer_name")
    val employerName: String?,
    @SerializedName("max_score")
    val maxScore: Int?,
    @SerializedName("num_stars")
    val numStars: Double?,
    @SerializedName("publisher")
    val publisher: String?,
    @SerializedName("review_count")
    val reviewCount: Int?,
    @SerializedName("reviews_link")
    val reviewsLink: String?,
    @SerializedName("score")
    val score: Double?
)