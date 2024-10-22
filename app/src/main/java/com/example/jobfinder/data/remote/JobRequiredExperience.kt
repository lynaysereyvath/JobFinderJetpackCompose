package com.example.jobfinder.data.remote


import com.google.gson.annotations.SerializedName

data class JobRequiredExperience(
    @SerializedName("experience_mentioned")
    val experienceMentioned: String?,
    @SerializedName("experience_preferred")
    val experiencePreferred: String?,
    @SerializedName("no_experience_required")
    val noExperienceRequired: String?,
    @SerializedName("required_experience_in_months")
    val requiredExperienceInMonths: String?
)