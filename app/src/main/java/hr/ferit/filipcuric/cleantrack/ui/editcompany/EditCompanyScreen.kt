package hr.ferit.filipcuric.cleantrack.ui.editcompany

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.filipcuric.cleantrack.ui.component.EditLogoCard
import hr.ferit.filipcuric.cleantrack.ui.theme.Green

@Composable
fun EditCompanyScreen(
    viewModel: EditCompanyViewModel,
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
                .padding(vertical=12.dp),
            color = MaterialTheme.colorScheme.tertiary
        )
        TextField(
            value = viewModel.name,
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
            onValueChange = { viewModel.onNameValueChange(it) }
        )
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri: Uri? -> uri?.let { viewModel.onImageSelected(it) }}
        )
        EditLogoCard(
            imageUrl = if(viewModel.imageUri == Uri.EMPTY) viewModel.imageUrl else viewModel.imageUri.toString(),
            onClick = { launcher.launch("image/*") },
            modifier = Modifier
                .height(300.dp)
                .padding(bottom = 10.dp)
        )
        Button(
            onClick = { viewModel.updateCompany() },
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
