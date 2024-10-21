package com.example.jobfinder.screens.home

import com.example.jobfinder.data.remote.JobDto

data class HomeScreenState(
    val popularJobs: List<JobDto>? = null,
    val isSearchingPopularJob: Boolean = false,
    val popularJobError: String? = null,

    val nearbyJobs: List<JobDto>? = null,
    val isSearchingNearbyJob: Boolean = false,
    val nearbyJobError: String? = null
)