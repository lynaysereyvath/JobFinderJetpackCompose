package com.example.jobfinder.screens.jobdetail

import com.example.jobfinder.domain.jobdetail.JobDetail

data class JobDetailState(
    val jobDetail: JobDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
