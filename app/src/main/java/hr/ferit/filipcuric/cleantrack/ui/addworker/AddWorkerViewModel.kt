package hr.ferit.filipcuric.cleantrack.ui.addworker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import hr.ferit.filipcuric.cleantrack.data.repository.UserRepository
import hr.ferit.filipcuric.cleantrack.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AddWorkerViewModel(
    private val userRepository: UserRepository,
    private val companyId: String,
    private val navController: NavController,
) : ViewModel() {

    var hasError by mutableStateOf(false)
        private set

    var searchValue by mutableStateOf("")
        private set

    fun onSearchValueChange(value: String) {
        searchValue = value
        hasError = false
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val foundWorkers: StateFlow<List<User>> =
        snapshotFlow { searchValue }
            .mapLatest { userRepository.getUsersByUsername(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = listOf()
            )

    fun addWorkerToCompany(userId: String) {
        viewModelScope.launch {

            if(userRepository.addUserToCompanyByCompanyId(userId, companyId)) {
                navController.popBackStack()
            } else {
                hasError = true
            }
        }
    }
}
