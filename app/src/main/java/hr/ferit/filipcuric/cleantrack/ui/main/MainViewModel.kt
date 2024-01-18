package hr.ferit.filipcuric.cleantrack.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.ferit.filipcuric.cleantrack.data.di.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    fun isSomeoneLoggedIn() : Boolean {
        var isSomeoneLoggedIn = false
        viewModelScope.launch(Dispatchers.IO) {
            isSomeoneLoggedIn = userRepository.fetchLoggedInUser().isNotEmpty()
        }
        return isSomeoneLoggedIn
    }
}
