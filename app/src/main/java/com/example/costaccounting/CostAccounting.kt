package com.example.costaccounting

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDate

public open class CostAccounting() : Parcelable {
    private var categoryExpenses = mutableMapOf(
        "Food" to 0f,
        "Public utilities" to 0f,
        "Car parts" to 0f,
        "Subscriptions" to 0f,
        "Fun" to 0f,
        "Taxi" to 0f,
        "Others" to 0f
    )

    @RequiresApi(Build.VERSION_CODES.O)
    private var expenses = mutableMapOf(
        LocalDate.of(2023, 11, 1) to mutableListOf<String>("Food*100.2"),
        LocalDate.of(2023, 11, 2) to mutableListOf<String>("Public utilities*120.2"),
        LocalDate.of(2023, 11, 3) to mutableListOf<String>("Car parts*10.2"),
        LocalDate.of(2023, 11, 4) to mutableListOf<String>("Subscriptions*5.2"),
        LocalDate.of(2023, 11, 5) to mutableListOf<String>("Food*1000.2"),
        LocalDate.of(2023, 11, 6) to mutableListOf<String>("Fun*10.2"),
        LocalDate.of(2023, 11, 7) to mutableListOf<String>("Food*240.2"),
        LocalDate.of(2023, 11, 8) to mutableListOf<String>("Taxi*20.2"),
        LocalDate.of(2023, 11, 9) to mutableListOf<String>("Others*60.2"),
        LocalDate.of(2023, 11, 10) to mutableListOf<String>("Food*70.2"),
    )

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(parcel: Parcel) : this() {
        readFromParcel(parcel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public fun getExpensesMap(): MutableMap<LocalDate, MutableList<String>> {
        return expenses
    }

    public fun getCategories(): List<String> {
        return categoryExpenses.keys.toList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public fun addCheck(category: String, check: Float, date: LocalDate): Boolean {
        if (categoryExpenses.containsKey(category) && check > 0) {
            categoryExpenses[category]?.let { currentExpense ->
                categoryExpenses[category] = currentExpense + check
                addCheckToDate(date, category, check)
                Log.d("addcheck", "$category, $check, ${date.toString()}")
                return true
            }
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addCheckToDate(date: LocalDate, category: String, check: Float) {
        expenses.computeIfAbsent(date) { mutableListOf() }.add("$category*$check")
        Log.d("addcheck", expenses[date].toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getWeeklyExpenses(): MutableMap<String, Float> {
        val weekExpenses = mutableMapOf<String, Float>()
        val daysInWeek = 7
        val today = LocalDate.now()
        val currentDate = LocalDate.now()

        for (i in 0 until daysInWeek) {
            val date = currentDate.minusDays(i.toLong())
            if (date.isAfter(today.minusDays((daysInWeek - 1).toLong()))) {
                expenses[date]?.forEach { expense ->
                    val expenseArray = expense.split('*')
                    weekExpenses.merge(expenseArray[0], expenseArray[1].toFloat()) { oldValue, newValue ->
                        oldValue + newValue
                    }
                }
            }
        }

        // Виводимо дані для відстеження
        println("getWeeklyExpenses for $currentDate: $weekExpenses")

        return weekExpenses
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun getMonthlyExpenses(): MutableMap<String, Float> {
        val monthExpenses = mutableMapOf<String, Float>()
        val currentDate = LocalDate.now()
        val daysInMonth = currentDate.month.length(currentDate.isLeapYear)

        for (i in 0 until daysInMonth) {
            val date = currentDate.minusDays(i.toLong())
            expenses[date]?.forEach { expense ->
                val expenseArray = expense.split('*')
                monthExpenses.merge(expenseArray[0], expenseArray[1].toFloat()) { oldValue, newValue ->
                    oldValue + newValue
                }
            }
        }

        return monthExpenses
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeMap(categoryExpenses)
        dest.writeMap(expenses)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun readFromParcel(parcel: Parcel) {
        categoryExpenses = parcel.readHashMap(Float::class.java.classLoader) as MutableMap<String, Float>
        expenses = parcel.readHashMap(String::class.java.classLoader) as MutableMap<LocalDate, MutableList<String>>
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CostAccounting> {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun createFromParcel(parcel: Parcel): CostAccounting {
            return CostAccounting(parcel)
        }

        override fun newArray(size: Int): Array<CostAccounting?> {
            return arrayOfNulls(size)
        }
    }
}
