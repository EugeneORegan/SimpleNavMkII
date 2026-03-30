package com.example.simplenav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navCont = rememberNavController() // 1. Need a NavController object
/*
First argument of a NavHost is the navController
Second argument is the start point
 */
            NavHost(navController = navCont, startDestination = "Start", builder = {
                composable(route = "Start")
                {
                    Start(navCont)
                }

                composable(route = "ThirdScreen") {
                    ThirdScreen(navCont)
                }

                composable(route = "FifthScreen") {
                    FifthScreen(navCont)
                }

                composable(route = "FourthScreen") {
                    FourthScreen(navCont)
                }

                composable(route = "SecondScreen/{name}",
                    arguments = listOf(navArgument("name") {
                        type = NavType.StringType
                    })
                )
                { backStackentry ->
                    val uname =
                        backStackentry.arguments?.getString("name") // Need to get value from here
                    println(uname)
                    SecondScreen(navCont, uname ?: "No name")
                }
            }) // End of NavHost

        }
    }


}