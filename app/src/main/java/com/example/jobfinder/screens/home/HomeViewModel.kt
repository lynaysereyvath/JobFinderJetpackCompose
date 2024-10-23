package com.example.jobfinder.screens.home

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
class HomeViewModel @Inject constructor(private val jobSearchRepository: JobSearchRepository) :
    ViewModel() {

    var homeState by mutableStateOf(HomeScreenState())
        private set

    fun searchPopularJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            homeState = homeState.copy(
                isSearchingPopularJob = true,
                popularJobError = null
            )
            when (val result = jobSearchRepository.searchJob("React Developer", 1)) {
                is Resource.Success -> {
                    homeState = homeState.copy(
                        popularJobs = result.data,
                        isSearchingPopularJob = false,
                        popularJobError = null
                    )
                }

                is Resource.Error -> {
                    homeState = homeState.copy(
                        popularJobs = null,
                        isSearchingPopularJob = false,
                        popularJobError = result.message
                    )
                }
            }
        }
    }

    fun searchNearbyJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            homeState = homeState.copy(
                isSearchingNearbyJob = true,
                nearbyJobError = null
            )
            when (val result = jobSearchRepository.searchJob("React Native", 1)) {
                is Resource.Success -> {
                    homeState = homeState.copy(
                        nearbyJobs = result.data,
                        isSearchingNearbyJob = false,
                        nearbyJobError = null
                    )
                }

                is Resource.Error -> {
                    homeState = homeState.copy(
                        nearbyJobs = null,
                        isSearchingNearbyJob = false,
                        nearbyJobError = result.message
                    )
                }
            }
        }
    }
}