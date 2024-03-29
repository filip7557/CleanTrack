package hr.ferit.filipcuric.cleantrack.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.filipcuric.cleantrack.ui.component.LoadingAnimation
import hr.ferit.filipcuric.cleantrack.ui.theme.Green

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        if (viewModel.loading) {
            LoadingAnimation()
        } else {
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
                    onValueChange = { viewModel.onUsernameValueChange(it) }
                )
                TextField(
                    value = viewModel.password,
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
                    onValueChange = { viewModel.onPasswordValueChange(it) },
                    visualTransformation = PasswordVisualTransformation()
                )
                if (viewModel.loginHasError)
                    Text(
                        text = "Wrong username or password.",
                        fontSize = 18.sp,
                        color = Color.Red
                    )
                Button(
                    onClick = {
                        viewModel.loginUser()
                        onLoginClick()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0, 90, 4),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Login")
                }
                Text(
                    text = "Don't have an account?",
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .padding(top = 50.dp)
                )
                Button(
                    onClick = onRegisterClick,
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
    }
}
