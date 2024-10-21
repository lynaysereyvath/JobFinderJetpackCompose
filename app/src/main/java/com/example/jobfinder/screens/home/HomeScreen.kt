package com.example.jobfinder.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jobfinder.screens.common.TopAppBarHeader
import com.example.jobfinder.ui.theme.LightWhite

@Composable
fun HomeScreen(navController: NavHostController) {

    val viewModel: HomeViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.searchPopularJobs()
        viewModel.searchNearbyJobs()
    }

    val localFocusManager = LocalFocusManager.current
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .pointerInput(Unit) {
                    detectTapGestures {
                        localFocusManager.clearFocus()
                    }
                }
                .background(LightWhite)
                .verticalScroll(rememberScrollState())
        ) {

            TopAppBarHeader(false, {}) { }
            Welcome(modifier = Modifier.padding(top = 20.dp))
            PopularJobs(
                viewModel.homeState.popularJobs,
                viewModel.homeState.popularJobError,
                viewModel.homeState.isSearchingPopularJob
            )
            NearbyJobs(
                viewModel.homeState.nearbyJobs,
                viewModel.homeState.nearbyJobError,
                viewModel.homeState.isSearchingNearbyJob
            )
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController()
    )
}