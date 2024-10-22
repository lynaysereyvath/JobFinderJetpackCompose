package com.example.jobfinder.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface JobSearchApi {

    @GET("search")
    suspend fun searchJobs(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("date_posted") datePosted: String = "all"
    ): JobDataDto

    @GET("job-details")
    suspend fun getJobDetails(@Query("job_id") jobId: String): JobDetailDto
}