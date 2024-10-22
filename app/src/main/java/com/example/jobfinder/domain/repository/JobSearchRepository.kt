package com.example.jobfinder.domain.repository

import com.example.jobfinder.data.remote.JobDto
import com.example.jobfinder.domain.jobdetail.JobDetail
import com.example.jobfinder.domain.util.Resource

interface JobSearchRepository {

    suspend fun searchJob(query: String, page: Int, datePosted: String = "all"): Resource<List<JobDto>>
    suspend fun getJobDetail(jobId: String): Resource<JobDetail>

}