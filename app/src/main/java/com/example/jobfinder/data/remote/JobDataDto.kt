package com.example.jobfinder.data.remote


import com.google.gson.annotations.SerializedName

data class JobDataDto(
    @SerializedName("data")
    val `data`: List<JobDto>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("messasge")
    val message: String?
)