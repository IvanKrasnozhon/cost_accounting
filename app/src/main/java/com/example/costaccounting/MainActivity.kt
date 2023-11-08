package com.example.costaccounting

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.costaccounting.ui.theme.CostAccountingTheme

class MainActivity : ComponentActivity() {
    var costAccounting : CostAccounting = CostAccounting()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CostAccountingTheme {
                HomeScreen().BuildScreen(costAccounting)
            }
        }
    }
}
