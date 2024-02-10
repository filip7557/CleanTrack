package hr.ferit.filipcuric.cleantrack.ui.companyinfo

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import hr.ferit.filipcuric.cleantrack.data.repository.CompanyRepository
import hr.ferit.filipcuric.cleantrack.data.repository.UserRepository
import hr.ferit.filipcuric.cleantrack.model.Company
import hr.ferit.filipcuric.cleantrack.model.Location
import hr.ferit.filipcuric.cleantrack.model.User
import hr.ferit.filipcuric.cleantrack.navigation.AddWorkerDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompanyInfoViewModel(
    private val userRepository: UserRepository,
    private val companyRepository: CompanyRepository,
    private val navController: NavController,
    val companyId: String,
) : ViewModel() {

    var company by mutableStateOf(Company())
        private set

    var locations by mutableStateOf(listOf<Location>())
        private set

    var workers by mutableStateOf(listOf<User>())
        private set

    var manager by mutableStateOf("")
        private set

    var isManager by mutableStateOf(false)
        private set

    var loading by mutableStateOf(true)
        private set

    init {
        getCompany()
    }

    private fun getCompany() {
        viewModelScope.launch {
            viewModelScope.launch {
                company = companyRepository.getCompanyFromId(companyId)
                Log.w("COMPANY_INFO", "${company.name} -> ${company.managerId}")
                getCompanyManager()
                isCurrentUserManager()
                getLocations()
                getWorkers()
                Log.d("LOCATIONS", locations.toString())
            }
        }
    }

    fun getWorkers() {
        viewModelScope.launch {
            loading = true
            workers = companyRepository.getWorkersFromCompanyId(companyId)
            loading = false
        }
    }

    private fun isCurrentUserManager() {
        var userId: String
        viewModelScope.launch(Dispatchers.IO) {
            userId = userRepository.fetchLoggedInUser()
            isManager = userId == company.managerId
        }
    }

    private fun getCompanyManager() {
        viewModelScope.launch {
            Log.w("GET_MANAGER", "MANAGER_ID: ${company.managerId}")
            val user = userRepository.getUserFromId(company.managerId)
            manager = "${user.firstname} ${user.lastname}\n${user.email}"
        }
    }

    fun getLocations() {
        viewModelScope.launch {
            if(!loading)
                loading = true
            locations = companyRepository.getLocationsFromCompanyId(companyId)
            loading = false
        }
    }

    fun deleteCompany() {
        viewModelScope.launch {
            navController.popBackStack()
            companyRepository.deleteCompanyById(companyId)
        }
    }

    fun onAddWorkerClick() {
        navController.navigate(
            AddWorkerDestination.createNavigation(companyId)
        )
    }

    fun removeWorker(userId: String?) {
        viewModelScope.launch {
            userRepository.removeWorkerByUserIdAndCompanyId(userId, companyId)
            getWorkers()
        }
    }
}
