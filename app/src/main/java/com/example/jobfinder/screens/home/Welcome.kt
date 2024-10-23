package com.example.jobfinder.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jobfinder.ui.theme.Gray
import com.example.jobfinder.ui.theme.Primary
import com.example.jobfinder.ui.theme.Secondary
import com.example.jobfinder.ui.theme.Tertiary
import com.example.jobfinder.ui.theme.White

val contractType = arrayOf("Full-time", "Part-time", "Contractor")

@Composable
fun Welcome(modifier: Modifier = Modifier, navController: NavController) {
    var searchTerm by remember { mutableStateOf("") }
    var selectedContractType by remember { mutableStateOf(contractType[0]) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {

        Text("Hello User", color = Secondary, fontSize = 20.sp)
        Text(
            "Find your perfect job",
            color = Primary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(
                modifier = Modifier
                    .height(48.dp)
                    .weight(1f)
                    .background(White, MaterialTheme.shapes.medium.copy(CornerSize(12.dp))),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = searchTerm,
                    onValueChange = { searchTerm = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    singleLine = true,
                )
            }
            Spacer(modifier = Modifier.width(12.dp))

            IconButton(
                onClick = {
                    if (searchTerm.isNotEmpty())
                        navController.navigate("job_search/${searchTerm}")
                },
                modifier = Modifier
                    .width(48.dp)
                    .aspectRatio(1f)
                    .background(
                        color = Tertiary,
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search icon",
                    tint = White
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(contractType) {
                OutlinedButton(
                    onClick = {
                        selectedContractType = it
                    },
                    contentPadding = PaddingValues(horizontal = 10.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (it == selectedContractType) Primary else Gray
                    )
                ) {
                    Text(
                        it,
                        color = if (it == selectedContractType) Primary else Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}
