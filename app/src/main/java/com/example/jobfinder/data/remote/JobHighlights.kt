package com.example.jobfinder.data.remote


import com.google.gson.annotations.SerializedName

data class JobHighlights(
    @SerializedName("Benefits")
    val benefits: List<String?>?,
    @SerializedName("Qualifications")
    val qualifications: List<String?>?,
    @SerializedName("Responsibilities")
    val responsibilities: List<String?>?
)