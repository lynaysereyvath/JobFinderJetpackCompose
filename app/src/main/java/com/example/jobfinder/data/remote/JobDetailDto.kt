package com.example.jobfinder.data.remote


import com.google.gson.annotations.SerializedName

data class JobDetailDto(
    @SerializedName("data")
    val `data`: List<JobDetailDataDto>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("message")
    val message: String?
)