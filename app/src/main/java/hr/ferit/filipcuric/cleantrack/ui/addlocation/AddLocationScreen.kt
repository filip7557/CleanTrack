package hr.ferit.filipcuric.cleantrack.ui.addlocation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.filipcuric.cleantrack.ui.theme.Green

@Composable
fun AddLocationScreen(
    viewModel: AddLocationViewModel,
    onAddLocationClick: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Add a location",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .padding(vertical = 12.dp)
            )
            TextField(
                value = viewModel.name,
                label = {
                    Text(text = "Name")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Green,
                    focusedLabelColor = Green,
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(),
                onValueChange = { viewModel.onNameChange(it) }
            )
            TextField(
                value = viewModel.address,
                label = {
                    Text(text = "Address")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Green,
                    focusedLabelColor = Green,
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(),
                onValueChange = { viewModel.onAddressChange(it) }
            )
            TextField(
                value = viewModel.frequency,
                label = {
                    Text(text = "X times/week")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Green,
                    focusedLabelColor = Green,
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(),
                onValueChange = { viewModel.onFrequencyChange(it) }
            )
            Button(
                onClick = {
                    viewModel.addLocation()
                    onAddLocationClick()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0, 90, 4),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Add location")
            }
        }
    }
}
