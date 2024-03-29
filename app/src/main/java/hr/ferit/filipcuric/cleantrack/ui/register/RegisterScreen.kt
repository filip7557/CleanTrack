package hr.ferit.filipcuric.cleantrack.ui.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import hr.ferit.filipcuric.cleantrack.ui.theme.Green

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onRegisterClick: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            item {
                TextField(
                    value = viewModel.username,
                    label = {
                        Text(text = "Username")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
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
            }
            item {
                TextField(
                    value = viewModel.email,
                    label = {
                        Text(text = "Email")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
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
                        text = "There is already an account with this email address. Log in instead.",
                        color = Color.Red
                    )
                }
                val emailIsNotEmail by viewModel.emailIsNotEmail.collectAsState()
                if (emailIsNotEmail && viewModel.email.isNotEmpty()) {
                    Text(
                        text = "Invalid email address.",
                        color = Color.Red
                    )
                }
            }
            item {
                TextField(
                    value = viewModel.firstname,
                    label = {
                        Text(text = "Firstname")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
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
            }
            item {
                TextField(
                    value = viewModel.lastname,
                    label = {
                        Text(text = "Lastname")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
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
            }
            item {
                TextField(
                    value = viewModel.password,
                    label = {
                        Text(text = "Password")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
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
                    },
                    visualTransformation = PasswordVisualTransformation()
                )
            }
            item {
                Button(
                    enabled = viewModel.areTextFieldsFilled(),
                    onClick = {
                        viewModel.registerUser()
                        onRegisterClick()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0, 90, 4),
                        contentColor = Color.White,
                        disabledContainerColor = Color.DarkGray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Register")
                }
            }
        }
    }
}
