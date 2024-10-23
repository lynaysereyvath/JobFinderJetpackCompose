package com.example.jobfinder.screens.search

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
class SearchViewModel @Inject constructor(private val repository: JobSearchRepository) :
    ViewModel() {

    var searchState by mutableStateOf(SearchScreenState())
        private set

    fun nextPage() {
        searchState = searchState.copy(
            page = searchState.page + 1
        )

        searchState.searchTerm?.let {
            search()
        }
    }

    fun previousPage() {
        if (searchState.page > 1) {
            searchState = searchState.copy(
                page = searchState.page - 1
            )
        }
    }

    fun setSearchTerm(searchTerm: String?) {
        if (searchTerm == null)
            searchState = searchState.copy(
                error = "search term is null"
            )
        else {
            searchState = searchState.copy(
                searchTerm = searchTerm
            )
            search()
        }
    }

    private fun search() {
        viewModelScope.launch(Dispatchers.IO) {
            searchState = searchState.copy(
                isLoading = true,
                error = null
            )
            if (searchState.searchTerm != null) {
                when (val result = repository.searchJob(searchState.searchTerm!!, 1)) {
                    is Resource.Success -> {
                        searchState = searchState.copy(isLoading = false, data = result.data)
                    }

                    is Resource.Error -> {
                        searchState = searchState.copy(isLoading = false, error = result.message)
                    }
                }
            } else {
                searchState = searchState.copy(
                    error = "search term is null",
                    isLoading = false
                )
            }
        }
    }
}