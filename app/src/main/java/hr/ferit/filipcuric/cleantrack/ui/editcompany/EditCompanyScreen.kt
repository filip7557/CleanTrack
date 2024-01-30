package hr.ferit.filipcuric.cleantrack.ui.editcompany

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.filipcuric.cleantrack.model.Company
import hr.ferit.filipcuric.cleantrack.ui.component.EditLogoCard
import hr.ferit.filipcuric.cleantrack.ui.theme.Green

data class EditCompanyScreenViewState(
    val company: Company,
)

@Composable
fun EditCompanyScreen(
    editCompanyScreenViewState: EditCompanyScreenViewState,
    onCreateClick: () -> Unit,
    onUploadClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Edit company",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(vertical=12.dp)
        )
        TextField(
            value = "",
            label = {
                Text(text = "Name")
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Green,
                focusedLabelColor = Green,
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(),
            onValueChange = { /*TODO*/ }
        )
        EditLogoCard(
            imageUrl = editCompanyScreenViewState.company.imageUrl,
            onClick = onUploadClick,
            modifier = Modifier
                .height(300.dp)
                .padding(bottom = 10.dp)
        )
        Button(
            onClick = onCreateClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0, 90, 4),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Save changes")
        }
    }
}
