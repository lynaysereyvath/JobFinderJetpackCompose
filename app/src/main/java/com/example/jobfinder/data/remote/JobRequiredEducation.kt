package com.example.jobfinder.data.remote


import com.google.gson.annotations.SerializedName

data class JobRequiredEducation(
    @SerializedName("associates_degree")
    val associatesDegree: Boolean?,
    @SerializedName("bachelors_degree")
    val bachelorsDegree: Boolean?,
    @SerializedName("degree_mentioned")
    val degreeMentioned: String?,
    @SerializedName("degree_preferred")
    val degreePreferred: String?,
    @SerializedName("high_school")
    val highSchool: Boolean?,
    @SerializedName("postgraduate_degree")
    val postgraduateDegree: Boolean?,
    @SerializedName("professional_certification")
    val professionalCertification: Boolean?,
    @SerializedName("professional_certification_mentioned")
    val professionalCertificationMentioned: String?
)