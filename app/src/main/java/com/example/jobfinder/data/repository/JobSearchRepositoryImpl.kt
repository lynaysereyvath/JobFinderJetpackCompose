package com.example.jobfinder.data.repository

import android.util.Log
import com.example.jobfinder.data.remote.JobDto
import com.example.jobfinder.data.remote.JobSearchApi
import com.example.jobfinder.domain.repository.JobSearchRepository
import com.example.jobfinder.domain.util.Resource
import com.google.gson.Gson
import javax.inject.Inject

class JobSearchRepositoryImpl @Inject constructor(private val api: JobSearchApi) :
    JobSearchRepository {
    override suspend fun searchJob(
        query: String,
        page: Int,
        datePosted: String
    ): Resource<List<JobDto>> {
        return try {
            val response = api.searchJobs(query, page, datePosted)
            response.data?.let {
                Resource.Success(it)
            } ?: run {
                Resource.Error("No data found")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}