package com.example.jobfinder.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.jobfinder.data.remote.JobDto
import com.example.jobfinder.ui.theme.Gray
import com.example.jobfinder.ui.theme.Primary
import com.example.jobfinder.ui.theme.White

@Composable
fun PopularJobs(data: List<JobDto>? = null, error: String? = null, isLoading: Boolean = false) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Popular jobs")
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
                    LazyRow(contentPadding = PaddingValues(8.dp)) {
                        items(data) {
                            PopularJobCard(it)
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
fun PopularJobCard(data: JobDto) {
    Card(
        modifier = Modifier
            .width(210.dp)
            .padding(horizontal = 7.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            White
        ),
        shape = MaterialTheme.shapes.medium.copy(CornerSize(20.dp))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            AsyncImage(
                model = data.employerLogo,
                contentDescription = "Company Logo",
                modifier = Modifier
                    .width(50.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Inside
            )
            Text(
                data.employerName ?: "",
                maxLines = 1,
                color = Gray,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 5.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                data.jobTitle ?: "",
                maxLines = 1,
                color = Primary,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis
            )
            Row(modifier = Modifier.padding(top = 5.dp)) {
                Text("${data.jobPublisher ?: ""}-", color = Primary, fontWeight = FontWeight.SemiBold)
                Text(data.jobCountry ?: "", color = Gray)
            }
        }
    }
}

@Composable
fun ErrorComponent(error: String) {
    Row {
        Text(error)
    }
}