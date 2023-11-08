
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.costaccounting.ui.theme.mainTextStyle

data class DictionaryEntry(val category: String, val value: Float)

@Composable
fun DictionaryEntryItem(dictionaryEntry: DictionaryEntry) {
    //Modifier.height(100.dp)
    Row (){
        Text(
            text = dictionaryEntry.category,
            style = mainTextStyle,
            textAlign = TextAlign.Justify,
            maxLines = 1,
            fontSize = 26.sp,
            modifier = Modifier
                .width(150.dp)
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
        Spacer(
            modifier = Modifier
                .width(44.dp)

        )
        Text(
            text = dictionaryEntry.value.toString(),
            style = mainTextStyle,
            maxLines = 1,
            fontSize = 26.sp,
            modifier = Modifier
                .width(150.dp)
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
    }
}