package hr.ferit.filipcuric.cleantrack.ui.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.ferit.filipcuric.cleantrack.data.di.repository.UserRepository
import hr.ferit.filipcuric.cleantrack.navigation.HOME_ROUTE
import hr.ferit.filipcuric.cleantrack.navigation.LOGIN_ROUTE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    fun onUsernameValueChange(value: String) {
        username = value
    }

    fun onPasswordValueChange(value: String) {
        password = value
    }

    fun loginUser() : String {
        var route = LOGIN_ROUTE
        runBlocking(Dispatchers.IO) {
            val user = userRepository.fetchUser(username)
            Log.d("USER", "${user.password} ?= $password")
            if(user.password == password) {
                userRepository.loginUser(user)
                route = HOME_ROUTE
            }
        }
        return route
    }
}
