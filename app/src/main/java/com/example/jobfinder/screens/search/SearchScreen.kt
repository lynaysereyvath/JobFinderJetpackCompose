package com.example.jobfinder.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jobfinder.screens.common.TopAppBarHeader
import com.example.jobfinder.screens.home.NearbyJobCard
import com.example.jobfinder.ui.theme.Gray
import com.example.jobfinder.ui.theme.LightWhite
import com.example.jobfinder.ui.theme.Primary
import com.example.jobfinder.ui.theme.Tertiary
import com.example.jobfinder.ui.theme.White

@Composable
fun SearchScreen(navController: NavController) {

    val viewModel: SearchViewModel = hiltViewModel()

    val searchTerm = navController.currentBackStackEntry?.arguments?.getString("search_term")

    LaunchedEffect(true) {
        viewModel.setSearchTerm(searchTerm)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .background(LightWhite)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBarHeader(modifier = Modifier, canClickBack = true, onLeftClick = {
                navController.popBackStack()
            }) { }

            Text(
                viewModel.searchState.searchTerm ?: "",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 15.dp, end = 15.dp)
            )

            Text(
                "Job Opportunities",
                color = Primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp, end = 15.dp)
            )


            if (viewModel.searchState.isLoading) {
                CircularProgressIndicator()
            } else if (viewModel.searchState.error != null || viewModel.searchState.data == null) {
                Text(viewModel.searchState.error ?: "Something went wrong")
            } else {
                LazyColumn(modifier = Modifier
                    .weight(1f)
                    .padding(top = 20.dp)) {
                    items(viewModel.searchState.data!!) {
                        NearbyJobCard(it) {
                            navController.navigate(it.jobId ?: "")
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (viewModel.searchState.page > 1)
                        Button(
                            onClick = {
                                viewModel.previousPage()
                            },
                            modifier = Modifier
                                .width(30.dp)
                                .aspectRatio(1f),
                            colors = ButtonColors(Tertiary, White, Gray, Gray),
                            shape = MaterialTheme.shapes.small,
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                                contentDescription = "Previous page",
                                tint = White,
                                modifier = Modifier
                                    .width(25.dp)
                                    .aspectRatio(1f)
                            )
                        }
                    Text(
                        "1",
                        color = Tertiary,
                        modifier = Modifier.padding(horizontal = 10.dp),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    if (!viewModel.searchState.data.isNullOrEmpty()) {
                        Button(
                            onClick = {
                                viewModel.nextPage()
                            },
                            modifier = Modifier
                                .width(30.dp)
                                .aspectRatio(1f),
                            shape = MaterialTheme.shapes.small,
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonColors(Tertiary, White, Gray, Gray),
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                                contentDescription = "Next page",
                                tint = White,
                                modifier = Modifier
                                    .width(25.dp)
                                    .aspectRatio(1f)
                            )
                        }
                    }
                }
            }
        }

    }
}