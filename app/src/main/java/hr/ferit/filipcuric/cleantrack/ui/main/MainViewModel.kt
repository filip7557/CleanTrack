package hr.ferit.filipcuric.cleantrack.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.ferit.filipcuric.cleantrack.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var isLoggedIn by mutableStateOf(false)

    fun isUserLoggedIn() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoggedIn = userRepository.fetchLoggedInUser() != ""
        }
    }
}
