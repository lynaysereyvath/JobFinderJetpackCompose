package com.example.jobfinder.screens.common

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jobfinder.ui.theme.Gray
import com.example.jobfinder.ui.theme.Gray2
import com.example.jobfinder.ui.theme.LightWhite
import com.example.jobfinder.ui.theme.White

@Composable
fun buttonStyle() = Modifier
    .background(
        color = White,
        shape = MaterialTheme.shapes.small.copy(CornerSize(12.dp))
    )
    .width(40.dp)
    .aspectRatio(1f)

@Composable
fun iconStyle() = Modifier
    .width(30.dp)
    .aspectRatio(1f)

@Composable
fun TopAppBarHeader(
    modifier: Modifier = Modifier,
    canClickBack: Boolean = false,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = {
                onLeftClick()
            },
            modifier = buttonStyle()
        ) {
            if (canClickBack)
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Navigate back button",
                    modifier = iconStyle()
                )
            else
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = "Menu icon",
                    modifier = iconStyle()
                )
        }

        IconButton(onClick = onRightClick, modifier = buttonStyle()) {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = "Profile icon",
                modifier = iconStyle()
            )
        }
    }
}