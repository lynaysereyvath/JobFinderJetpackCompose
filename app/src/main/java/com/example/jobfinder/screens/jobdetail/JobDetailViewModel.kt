package com.example.jobfinder.screens.jobdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobfinder.domain.repository.JobSearchRepository
import com.example.jobfinder.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobDetailViewModel @Inject constructor(private val repository: JobSearchRepository) :
    ViewModel() {

    var detailState by mutableStateOf(JobDetailState())
        private set

    fun getDetail(jobId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            detailState = detailState.copy(
                isLoading = true,
                error = null
            )
            when (val result = repository.getJobDetail(jobId)) {
                is Resource.Success -> {
                    detailState = detailState.copy(
                        jobDetail = result.data,
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    detailState = detailState.copy(
                        jobDetail = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}