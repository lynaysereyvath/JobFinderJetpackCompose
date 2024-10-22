package com.example.jobfinder.domain.jobdetail

import com.example.jobfinder.data.remote.ApplyOption

data class JobDetail(
    val jobId: String?,
    val applyLink: String?  ,
    val companyLogo: String?,
    val jobTitle: String?,
    val employerName: String?,
    val jobLocation: String?,
    val jobDescription: String?,
    val jobQualification: List<String>?,
    val jobResponsibilities: List<String>?,
)
