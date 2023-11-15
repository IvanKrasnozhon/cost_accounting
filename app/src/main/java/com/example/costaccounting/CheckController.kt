package com.example.costaccounting

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.input.TextFieldValue
import java.time.LocalDate

class CheckController {
    @RequiresApi(Build.VERSION_CODES.O)
    public fun addCheck(mDate: String, costAccounting: CostAccounting, selectedOption : String, check : TextFieldValue) {
        val tempDate = mDate.split('/')
        costAccounting.addCheck(
            selectedOption,
            check.text.toFloat(),
            LocalDate.of(
                tempDate[2].toInt(),
                tempDate[1].toInt(),
                tempDate[0].toInt()
            )
        )
    }
}