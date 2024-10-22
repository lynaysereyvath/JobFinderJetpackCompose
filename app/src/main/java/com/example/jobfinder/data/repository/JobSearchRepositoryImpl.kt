package com.example.jobfinder.data.repository

import com.example.jobfinder.data.mappers.toJobDetail
import com.example.jobfinder.data.remote.JobDto
import com.example.jobfinder.data.remote.JobSearchApi
import com.example.jobfinder.domain.jobdetail.JobDetail
import com.example.jobfinder.domain.repository.JobSearchRepository
import com.example.jobfinder.domain.util.Resource
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

    override suspend fun getJobDetail(jobId: String): Resource<JobDetail> {
        return try {
            val response = api.getJobDetails(jobId)
            if (response.status == "OK") {
                response.toJobDetail().let {
                    Resource.Success(it)
                }
            } else {
                Resource.Error(response.message ?: "Something went wrong")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}