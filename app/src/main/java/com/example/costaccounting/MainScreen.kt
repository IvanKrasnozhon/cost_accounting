package com.example.costaccounting

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.costaccounting.ui.theme.buttonTextStyle
import com.example.costaccounting.ui.theme.mainTextStyle


@SuppressLint("MutableCollectionMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navController: NavController, costAccounting: CostAccounting) {
    val mainScreenViewModel by remember { mutableStateOf(MainScreenViewModel(costAccounting)) }
    var buttonPeriod by rememberSaveable { mutableStateOf(mainScreenViewModel.expensesPeriod.value)}
    var totalExpenses by rememberSaveable {
        mutableFloatStateOf(mainScreenViewModel.totalSum.floatValue)
    }
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {

        Text(
            text = "$${totalExpenses}",
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
                    mainScreenViewModel.changeExpenses()
                    buttonPeriod = mainScreenViewModel.expensesPeriod.value
                    totalExpenses = mainScreenViewModel.totalSum.floatValue
                },
                Modifier
                    .height(90.dp)
                    .weight(0.45f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEA6262)),
                shape = RoundedCornerShape(size = 15.dp),

                ) {
                Text(text = "Current period: $buttonPeriod", style = buttonTextStyle)
            }
        }

        Spacer(Modifier.height(20.dp))

        val columnWidth = if(isPortrait) {
            548.dp
        } else {
            1000.dp
        }

        LazyColumn(
            modifier = Modifier
                .shadow(
                    elevation = 59.dp,
                    spotColor = Color(0xFF123123),
                    ambientColor = Color(0xFF123123)
                )
                .width(columnWidth)
                .height(503.dp)
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 15.dp)
                )
                .padding(19.dp, 17.dp)

        ) {
            if (mainScreenViewModel.expensesPeriod.value != buttonPeriod) {
                mainScreenViewModel.changeExpenses()
            }
            items(mainScreenViewModel.currentExpenses.value.entries.toList()) { (category, value) ->
                val dictionaryEntry = DictionaryEntry(category, value)
                DictionaryEntryItem(dictionaryEntry)
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
