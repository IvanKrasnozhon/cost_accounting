package com.example.costaccounting

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



open class HomeScreen {
    @SuppressLint("NotConstructor")
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    public fun BuildScreen(costAccounting: CostAccounting) {
        val navController = rememberNavController()
        var costAccountingSave by remember { mutableStateOf(costAccounting) }
        costAccountingSave = costAccounting
        NavHost(navController = navController, startDestination = "main_screen") {
            composable("main_screen") {
                MainScreen(navController = navController, costAccounting)
            }
            composable("addCheck_screen") {
                AddCheckScreen(navController = navController, costAccounting)
            }
        }
    }
}