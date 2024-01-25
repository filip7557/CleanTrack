package hr.ferit.filipcuric.cleantrack.ui.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import hr.ferit.filipcuric.cleantrack.data.repository.UserRepository
import hr.ferit.filipcuric.cleantrack.navigation.HOME_ROUTE
import hr.ferit.filipcuric.cleantrack.navigation.LOGIN_ROUTE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait

class LoginViewModel(
    private val userRepository: UserRepository,
    private val navController: NavController,
) : ViewModel() {

    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    var loginHasError by mutableStateOf(false)

    fun onUsernameValueChange(value: String) {
        username = value
    }

    fun onPasswordValueChange(value: String) {
        password = value
    }

    fun loginUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.loginUser(username, password)
            val userId = userRepository.fetchLoggedInUser()
            Log.d("LOGIN_USERID", "User ID: $userId")
            if (userId != "")
                viewModelScope.launch {
                    navController.navigate(HOME_ROUTE) {
                        popUpTo(LOGIN_ROUTE) {
                            inclusive = true
                        }
                    }
                }
            else {
                loginHasError = true
            }
        }
    }
}
