package com.example.costaccounting

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import java.util.Calendar

class MainScreenViewModel(private var costAccounting: CostAccounting) {
    private val calendar: Calendar = Calendar.getInstance()
    @SuppressLint("MutableCollectionMutableState")
    @RequiresApi(Build.VERSION_CODES.O)
    public var currentExpenses = mutableStateOf(costAccounting.getWeeklyExpenses())
        private set
    public var expensesPeriod = mutableStateOf("Week")
        private set
    @RequiresApi(Build.VERSION_CODES.O)
    public var totalSum = mutableFloatStateOf(currentExpenses.value.values.sum())
        private set
    @RequiresApi(Build.VERSION_CODES.O)
    public fun changeExpenses() {
        if(expensesPeriod.value == "Week") {
            currentExpenses.value = costAccounting.getMonthlyExpenses()
            expensesPeriod.value = "Month"
        } else {
            currentExpenses.value = costAccounting.getWeeklyExpenses()
            expensesPeriod.value = "Week"
        }
        totalSum.floatValue = currentExpenses.value.values.sum()
    }
}