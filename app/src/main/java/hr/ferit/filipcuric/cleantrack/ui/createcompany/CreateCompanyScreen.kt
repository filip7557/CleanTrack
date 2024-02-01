package hr.ferit.filipcuric.cleantrack.ui.createcompany

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
import hr.ferit.filipcuric.cleantrack.ui.component.LoadingAnimation
import hr.ferit.filipcuric.cleantrack.ui.component.UploadLogoCard
import hr.ferit.filipcuric.cleantrack.ui.theme.Green

@Composable
fun CreateCompanyScreen(
    viewModel: CreateCompanyViewModel,
    onCreateClick: () -> Unit,
) {
    if(viewModel.loading) {
        LoadingAnimation()
    } else {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Create a new company",
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(vertical = 12.dp)
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
            onValueChange = {
                viewModel.onNameValueChange(it)
            }
        )
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri: Uri? -> uri?.let { viewModel.onImageSelected(it) } }
        )
        UploadLogoCard(
            onClick = {
                launcher.launch("image/*")
            },
            imageUri = viewModel.imageUri,
            modifier = Modifier
                .height(300.dp)
                .padding(bottom = 10.dp)
        )
        Button(
            onClick = {
                viewModel.createCompany()
                onCreateClick()
            },
            enabled = viewModel.name.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0, 90, 4),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Create")
        }
    }
    }
}
