package hr.ferit.filipcuric.cleantrack.ui.register

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.ferit.filipcuric.cleantrack.ui.theme.Green
import hr.ferit.filipcuric.cleantrack.data.UserRepository

@Composable
fun RegisterRoute(
    viewModel: RegisterViewModel,
    onRegisterClick: () -> Unit,
) {

    RegisterScreen(
        viewModel = viewModel,
        onRegisterClick = {
            onRegisterClick()
        }
    )
}

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onRegisterClick: (Unit) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
    ) {
        TextField(
            value = viewModel.username,
            label = {
                Text(text = "Username")
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Green,
                focusedLabelColor = Green,
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(),
            onValueChange = { value ->
                viewModel.onUsernameValueChange(value)
            }
        )
        val userNameHasError by viewModel.userNameHasError.collectAsState()
        if (userNameHasError) {
            Text(
                text = "Username not available. Please choose a different one.",
                color = Color.Red
            )
        }
        TextField(
            value = viewModel.email,
            label = {
                Text(text = "Email")
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Green,
                focusedLabelColor = Green,
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(),
            onValueChange = { value ->
                viewModel.onEmailValueChange(value)
            }
        )
        val emailHasError by viewModel.emailHasError.collectAsState()
        if (emailHasError) {
            Text(
                text = "There is already an account with this email. Log in instead.",
                color = Color.Red
            )
        }
        TextField(
            value = viewModel.firstname,
            label = {
                Text(text = "Firstname")
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Green,
                focusedLabelColor = Green,
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(),
            onValueChange = { value ->
                viewModel.onFirstnameValueChange(value)
            }
        )
        TextField(
            value = viewModel.lastname,
            label = {
                Text(text = "Lastname")
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Green,
                focusedLabelColor = Green,
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(),
            onValueChange = { value ->
                viewModel.onLastnameValueChange(value)
            }
        )
        var password = ""
        for (char in viewModel.password)
            password += '*'
        TextField(
            value = password,
            label = {
                Text(text = "Password")
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Green,
                focusedLabelColor = Green,
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(),
            onValueChange = { value ->
                viewModel.onPasswordValueChange(value)
            }
        )
        Button(
            enabled = !userNameHasError && !emailHasError && viewModel.areTextFieldFilled(),
            onClick = {
                onRegisterClick(viewModel.registerUser())
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0, 90, 4),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Register")
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterRoute(viewModel = RegisterViewModel(UserRepository()), onRegisterClick = {})
}
