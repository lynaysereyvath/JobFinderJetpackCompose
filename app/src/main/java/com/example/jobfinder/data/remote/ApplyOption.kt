package com.example.jobfinder.data.remote


import com.google.gson.annotations.SerializedName

data class ApplyOption(
    @SerializedName("apply_link")
    val applyLink: String?,
    @SerializedName("is_direct")
    val isDirect: Boolean?,
    @SerializedName("publisher")
    val publisher: String?
)