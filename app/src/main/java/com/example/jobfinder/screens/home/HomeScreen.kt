package com.example.jobfinder.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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

    if (viewModel.homeState.popularJobs == null)
        viewModel.searchPopularJobs()
    if (viewModel.homeState.nearbyJobs == null)
        viewModel.searchNearbyJobs()

    val localFocusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .background(LightWhite)
            .fillMaxSize()
            .safeDrawingPadding(),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBarHeader(canClickBack = false, onLeftClick = {}) { }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures {
                            localFocusManager.clearFocus()
                        }
                    }
                    .verticalScroll(rememberScrollState())
            ) {

                Welcome(modifier = Modifier.padding(top = 20.dp))
                PopularJobs(
                    navController,
                    viewModel.homeState.popularJobs,
                    viewModel.homeState.popularJobError,
                    viewModel.homeState.isSearchingPopularJob
                )
                NearbyJobs(
                    navController,
                    viewModel.homeState.nearbyJobs,
                    viewModel.homeState.nearbyJobError,
                    viewModel.homeState.isSearchingNearbyJob
                )
            }
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