package com.example.jobfinder.data.remote


import com.google.gson.annotations.SerializedName

data class EstimatedSalary(
    @SerializedName("job_title")
    val jobTitle: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("max_salary")
    val maxSalary: Double?,
    @SerializedName("median_salary")
    val medianSalary: Double?,
    @SerializedName("min_salary")
    val minSalary: Double?,
    @SerializedName("publisher_link")
    val publisherLink: String?,
    @SerializedName("publisher_name")
    val publisherName: String?,
    @SerializedName("salary_currency")
    val salaryCurrency: String?,
    @SerializedName("salary_period")
    val salaryPeriod: String?
)