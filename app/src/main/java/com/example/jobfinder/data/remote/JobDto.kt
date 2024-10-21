package com.example.jobfinder.data.remote


import com.google.gson.annotations.SerializedName

data class JobDto(
    @SerializedName("apply_options")
    val applyOptions: List<ApplyOption?>?,
    @SerializedName("employer_logo")
    val employerLogo: String?,
    @SerializedName("employer_name")
    val employerName: String?,
    @SerializedName("job_country")
    val jobCountry: String?,
    @SerializedName("job_description")
    val jobDescription: String?,
    @SerializedName("job_employment_type")
    val jobEmploymentType: String?,
    @SerializedName("job_highlights")
    val jobHighlights: JobHighlights?,
    @SerializedName("job_id")
    val jobId: String?,
    @SerializedName("job_publisher")
    val jobPublisher: String?,
    @SerializedName("job_title")
    val jobTitle: String?
)