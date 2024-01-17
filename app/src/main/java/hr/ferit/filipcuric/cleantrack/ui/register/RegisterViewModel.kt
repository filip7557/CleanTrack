package hr.ferit.filipcuric.cleantrack.ui.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.ferit.filipcuric.cleantrack.data.UserRepository
import hr.ferit.filipcuric.cleantrack.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

class RegisterViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    var username by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var firstname by mutableStateOf("")
        private set
    var lastname by mutableStateOf("")
        private set

    @OptIn(ExperimentalCoroutinesApi::class)
    val userNameHasError: StateFlow<Boolean> =
        snapshotFlow { username }
            .mapLatest { !userRepository.isUsernameAvailable(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false
            )

    @OptIn(ExperimentalCoroutinesApi::class)
    val emailHasError: StateFlow<Boolean> =
        snapshotFlow { email }
            .mapLatest { !userRepository.isEmailAvailable(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false
            )


    fun onUsernameValueChange(value: String) {
        username = value
    }

    fun onEmailValueChange(value: String) {
        email = value
    }

    fun onPasswordValueChange(value: String) {
        password = value
    }

    fun onFirstnameValueChange(value: String) {
        firstname = value
    }

    fun onLastnameValueChange(value: String) {
        lastname = value
    }

    fun registerUser() {
        userRepository.createUser(
            User(
                username = username,
                email = email,
                password = password,
                firstname = firstname,
                lastname = lastname,
            )
        )
    }

    fun areTextFieldFilled() : Boolean {
        return username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && firstname.isNotEmpty() && lastname.isNotEmpty()
    }
}
