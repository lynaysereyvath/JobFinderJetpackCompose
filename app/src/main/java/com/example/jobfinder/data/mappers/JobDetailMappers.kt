package com.example.jobfinder.data.mappers

import com.example.jobfinder.data.remote.JobDetailDto
import com.example.jobfinder.domain.jobdetail.JobDetail

fun JobDetailDto.toJobDetail(): JobDetail {
    val data = this.data?.get(0)
    return JobDetail(
        jobId = data?.jobId,
        jobTitle = data?.jobTitle,
        companyLogo = data?.employerLogo,
        employerName = data?.employerName,
        jobLocation = data?.jobCountry,
        jobDescription = data?.jobDescription,
        jobResponsibilities = data?.jobHighlights?.responsibilities,
        jobQualification = data?.jobHighlights?.qualifications,
        applyLink = data?.jobApplyLink
    )
}