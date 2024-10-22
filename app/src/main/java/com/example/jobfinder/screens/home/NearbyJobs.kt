package com.example.jobfinder.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.jobfinder.data.remote.JobDto
import com.example.jobfinder.ui.theme.Gray2
import com.example.jobfinder.ui.theme.Primary
import com.example.jobfinder.ui.theme.Secondary
import com.example.jobfinder.ui.theme.White

@Composable
fun NearbyJobs(
    navController: NavController,
    data: List<JobDto>? = null,
    error: String? = null,
    isLoading: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Nearby jobs")
            TextButton(onClick = {}) {
                Text("Show all")
            }
        }

        if (isLoading) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            if (error != null) {
                ErrorComponent(error)
            } else {
                if (data != null) {
                    Column {
                        for (i in data) {
                            NearbyJobCard(i) {
                                navController.navigate("job_detail/${i.jobId}")
                            }
                        }
                    }
                } else {
                    ErrorComponent("Something went wrong")
                }
            }
        }


    }
}

@Composable
fun NearbyJobCard(
    data: JobDto,
    handleClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 7.dp),
        colors = CardDefaults.cardColors(White),
        elevation = CardDefaults.cardElevation(2.dp),
        onClick = handleClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = "",
                contentDescription = data.employerLogo,
                modifier = Modifier
                    .width(50.dp)
                    .aspectRatio(1f)
                    .background(Gray2, MaterialTheme.shapes.medium),
                contentScale = ContentScale.Inside
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(
                    data.jobTitle ?: "",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    data.jobEmploymentType?.lowercase()?.replaceFirstChar { it.uppercase() } ?: "",
                    fontSize = 14.sp,
                    color = Secondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}