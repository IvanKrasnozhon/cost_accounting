package com.example.costaccounting

import DictionaryEntry
import DictionaryEntryItem
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.costaccounting.ui.theme.buttonTextStyle
import com.example.costaccounting.ui.theme.mainTextStyle
import java.time.LocalDate



@SuppressLint("MutableCollectionMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navController: NavController, costAccounting: CostAccounting) {
    var currentExpenses by remember { mutableStateOf(costAccounting.getWeeklyExpenses(LocalDate.of(2023, 11, 10))) }
    var totalSum: Float by remember { mutableFloatStateOf(currentExpenses.values.sum()) }
    var temp = costAccounting.getWeeklyExpenses(LocalDate.of(2023, 11, 10))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {

        Text(
            text = "$" + totalSum,
            modifier = Modifier
                .width(474.dp)
                .height(77.dp),
            style = mainTextStyle,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(10.dp))

        Row {
            Button(
                onClick = {
                    currentExpenses = costAccounting.getWeeklyExpenses(LocalDate.of(2023, 11, 10))
                    totalSum = currentExpenses.values.sum()
                },
                Modifier
                    .height(90.dp)
                    .weight(0.45f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEA6262)),
                shape = RoundedCornerShape(size = 15.dp),

                ) {
                Text(text = "Week", style = buttonTextStyle)

            }

            Spacer(Modifier.width(20.dp))

            Button(
                onClick = {
                    currentExpenses = costAccounting.getMonthlyExpenses(LocalDate.of(2023, 11, 10))
                    totalSum = currentExpenses.values.sum()
                },
                Modifier
                    .height(90.dp)
                    .weight(0.45f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEA6262)),
                shape = RoundedCornerShape(size = 15.dp)
            ) {
                Text(text = "Month", style = buttonTextStyle)
            }
        }

        Spacer(Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier
                .shadow(
                    elevation = 59.dp,
                    spotColor = Color(0xFF123123),
                    ambientColor = Color(0xFF123123)
                )
                .width(548.dp)
                .height(503.dp)
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 15.dp)
                )
                .padding(19.dp, 17.dp)

        ) {
            Log.d("MAP_INFO", "${currentExpenses.size}")

            items(currentExpenses.entries.toList()) { (category, value) ->
                val dictionaryEntry = DictionaryEntry(category, value)
                DictionaryEntryItem(dictionaryEntry)
                Log.d("INSERT", "$category: $value")
            }
        }
        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate("addCheck_screen")
            },
            Modifier
                .height(90.dp)
                //.padding(20.dp, 0.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEA6262)),
            shape = RoundedCornerShape(size = 15.dp),

            ) {
            Text(text = "Add check", style = buttonTextStyle)
        }
    }
}
