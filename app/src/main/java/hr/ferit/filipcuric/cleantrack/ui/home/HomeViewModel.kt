package hr.ferit.filipcuric.cleantrack.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.ferit.filipcuric.cleantrack.data.repository.CompanyRepository
import hr.ferit.filipcuric.cleantrack.data.repository.UserRepository
import hr.ferit.filipcuric.cleantrack.model.Company
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository,
    private val companyRepository: CompanyRepository,
) : ViewModel() {

    var companies by mutableStateOf(listOf<Company>())
    var userId: String = ""

    fun getCompanies() {
        viewModelScope.launch(Dispatchers.IO) {
            userId = userRepository.fetchLoggedInUser()
            Log.w("COMPANY VM", "GETTING COMPANIES FOR $userId")
            companies = companyRepository.fetchUserCompanies(userId)
        }
    }
}
