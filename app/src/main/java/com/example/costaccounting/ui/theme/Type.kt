package com.example.costaccounting.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.costaccounting.R

// Set of Material typography styles to start with
val Typography = Typography(
        bodyLarge = TextStyle(
                fontSize = 40.sp,
                lineHeight = 45.27.sp,
                fontFamily = FontFamily(Font(R.font.open_sans_bold)),
                fontWeight = FontWeight.Bold,
                color = MainText,
                textAlign = TextAlign.Center,
        )
)