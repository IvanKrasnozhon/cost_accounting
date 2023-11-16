package com.example.costaccounting

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.costaccounting.ui.theme.CostAccountingTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MyViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CostAccountingTheme {
                HomeScreen().BuildScreen(viewModel.costAccounting)
            }
        }
    }
}
