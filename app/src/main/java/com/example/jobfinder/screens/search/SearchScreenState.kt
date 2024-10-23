package com.example.jobfinder.screens.search

import com.example.jobfinder.data.remote.JobDto

data class SearchScreenState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val page: Int = 1,
    val searchTerm: String? = null,
    val data: List<JobDto>? = null
)