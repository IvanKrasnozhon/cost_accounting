package com.example.costaccounting

import android.app.DatePickerDialog
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.costaccounting.ui.theme.buttonTextStyle
import com.example.costaccounting.ui.theme.placeholderTextStyle
import com.example.costaccounting.ui.theme.textFieldTextStyle
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddCheckScreen(navController: NavController, costAccounting: CostAccounting) {
    var check by remember { mutableStateOf(TextFieldValue("")) }
    val radioOptions = costAccounting.getCategories()
    var selectedOption by remember { mutableStateOf(radioOptions[0]) }

// Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    var dstr = ""
    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
            dstr = "$mDayOfMonth*${mMonth + 1}*$mYear"
        },
        mYear, mMonth, mDay,
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {


        Text(text = "Selected Date: ${mDate.value}", fontSize = 30.sp, textAlign = TextAlign.Center)

        TextField(
            value = check,
            onValueChange = {
                check = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = {
                Text(
                    text = "Sum...",
                    Modifier.fillMaxWidth(),
                    style = placeholderTextStyle
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 59.dp,
                    spotColor = Color(0xFFD0D1D6),
                    ambientColor = Color(0xFFD0D1D6)
                ),
            textStyle = textFieldTextStyle,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .padding(8.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            radioOptions.forEach { categoryName ->
                Row {
                    RadioButton(
                        selected = (categoryName == selectedOption),
                        onClick = { selectedOption = categoryName }
                    )
                    Text(
                        text = categoryName
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                mDatePickerDialog.show()
            },
            Modifier
                .height(90.dp)
                .fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEA6262)),
            shape = RoundedCornerShape(size = 15.dp),

            ) {
            Text(
                text = "Select Date",
                style = buttonTextStyle,
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {

                val tempDate = mDate.value.split('/')
                Log.d("datepicker", dstr)
                costAccounting.addCheck(
                    selectedOption,
                    check.text.toFloat(),
                    LocalDate.of(
                        tempDate[2].toInt(),
                        tempDate[1].toInt(),
                        tempDate[0].toInt()
                    )
                )
                check = TextFieldValue("")

            },
            Modifier
                .height(90.dp)
                .fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEA6262)),
            shape = RoundedCornerShape(size = 15.dp),

            ) {
            Text(
                text = "Add Check",
                style = buttonTextStyle,
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.navigate("main_screen")
            },
            Modifier
                .height(90.dp)
                .fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEA6262)),
            shape = RoundedCornerShape(size = 15.dp),

            ) {
            Text(
                text = "Back",
                style = buttonTextStyle,
                maxLines = 1
            )
        }
    }
}