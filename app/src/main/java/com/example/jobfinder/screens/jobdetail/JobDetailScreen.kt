package com.example.jobfinder.screens.jobdetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.jobfinder.screens.common.TopAppBarHeader
import com.example.jobfinder.ui.theme.Gray
import com.example.jobfinder.ui.theme.Gray2
import com.example.jobfinder.ui.theme.LightWhite
import com.example.jobfinder.ui.theme.Primary
import com.example.jobfinder.ui.theme.Tertiary
import com.example.jobfinder.ui.theme.White

private val tabs = listOf("About", "Qualifications", "Responsibilities")

@Composable
fun JobDetailScreen(navController: NavHostController) {

    val viewModel: JobDetailViewModel = hiltViewModel()

    var selectedTab by remember { mutableStateOf(tabs[0]) }

    val jobId = navController.currentBackStackEntry?.arguments?.getString("jobId")

    LaunchedEffect(Unit) {
        if (jobId != null)
            viewModel.getDetail(jobId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .background(LightWhite)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBarHeader(canClickBack = true, onLeftClick = {
                navController.popBackStack()
            }) { }

            Box(modifier = Modifier.weight(1f)) {
                if (viewModel.detailState.isLoading) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (viewModel.detailState.error != null || viewModel.detailState.jobDetail == null) {
                    Text(viewModel.detailState.error ?: "Something went wrong")
                } else {
                    val detail = viewModel.detailState.jobDetail!!
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = detail.companyLogo,
                            contentDescription = "Company logo",
                            modifier = Modifier
                                .width(50.dp)
                                .aspectRatio(1f)
                                .background(
                                    White,
                                    shape = MaterialTheme.shapes.medium.copy(CornerSize(12.dp))
                                )
                        )
                        Text(
                            detail.jobTitle ?: "",
                            modifier = Modifier.padding(top = 20.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Row(
                            modifier = Modifier.padding(top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("${detail.employerName}/")
                            Icon(
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = "location icon",
                                modifier = Modifier
                                    .width(14.dp)
                                    .aspectRatio(1f)
                            )
                            Text(detail.jobLocation ?: "")
                        }

                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            items(tabs) {
                                Button(
                                    onClick = {
                                        selectedTab = it
                                    },
                                    shape = MaterialTheme.shapes.medium.copy(
                                        CornerSize(12.dp)
                                    ),
                                    colors = ButtonColors(
                                        if (selectedTab == it) Primary else White,
                                        if (selectedTab == it) White else Gray2,
                                        Gray,
                                        Gray
                                    ),
                                    contentPadding = PaddingValues(horizontal = 15.dp)
                                ) {
                                    Text(it, fontSize = 12.sp)
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                        }

                        Text(
                            when (selectedTab) {
                                tabs[0] -> "About this jos:"
                                tabs[1] -> "Qualifications:"
                                else -> "Responsibilities:"
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )

                        Text(
                            when (selectedTab) {
                                tabs[0] -> detail.jobDescription ?: ""
                                tabs[1] -> detail.jobQualification?.joinToString(" ") ?: ""
                                else -> detail.jobResponsibilities?.joinToString(" ") ?: ""
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        )
                    }
                }
            }

            Footer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )

        }
    }
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .width(50.dp)
                .aspectRatio(1f),
            contentPadding = PaddingValues(0.dp),
            border = BorderStroke(1.dp, Tertiary),
            shape = MaterialTheme.shapes.medium
        ) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite",
                modifier = Modifier
                    .width(30.dp)
                    .aspectRatio(1f),
                tint = Tertiary
            )
        }

        Spacer(modifier = Modifier.width(15.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonColors(Tertiary, White, Gray2, Gray2)
        ) {
            Text("Apply for job", color = White)
        }
    }
}

