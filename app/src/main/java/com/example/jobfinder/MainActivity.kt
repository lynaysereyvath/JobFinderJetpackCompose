package com.example.jobfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jobfinder.screens.home.HomeScreen
import com.example.jobfinder.screens.jobdetail.JobDetailScreen
import com.example.jobfinder.ui.theme.JobFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            JobFinderTheme {
                NavHost(
                    navController = navController, startDestination = "home"
                ) {
                    composable(
                        "home",
                        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right) },
                        exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
                        popEnterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Right,
                                initialOffset = { -300 })
                        },
                        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right) }
                    ) {
                        HomeScreen(navController)
                    }
                    composable(
                        route = "job_detail/{jobId}",
                        arguments = listOf(navArgument("jobId") { type = NavType.StringType }),
                        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
                        exitTransition = { null },
                        popEnterTransition = { null },
                        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right) }
                    ) { JobDetailScreen(navController) }
                }
            }
        }
    }
}
