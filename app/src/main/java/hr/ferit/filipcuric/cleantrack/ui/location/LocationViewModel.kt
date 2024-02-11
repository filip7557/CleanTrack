package hr.ferit.filipcuric.cleantrack.ui.location

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.ferit.filipcuric.cleantrack.data.repository.CompanyRepository
import hr.ferit.filipcuric.cleantrack.data.repository.UserRepository
import hr.ferit.filipcuric.cleantrack.model.Location
import hr.ferit.filipcuric.cleantrack.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LocationViewModel(
    private val userRepository: UserRepository,
    private val companyRepository: CompanyRepository,
    private val locationId: String,
    val isUserCompanyManager: Boolean,
) : ViewModel() {

    var location by mutableStateOf(Location())
        private set

    var cleaners by mutableStateOf(listOf<User>())
        private set

    var jobs by mutableStateOf(listOf<String>())
        private set

    var job by mutableStateOf("")
        private set

    var cleaner by mutableStateOf("")
        private set

    init {
        getLocation()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val foundCleaners: StateFlow<List<User>> =
        snapshotFlow { cleaner }
            .mapLatest { userRepository.getUsersByUsername(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = listOf()
            )

    private fun getLocation() {
        viewModelScope.launch {
            location = companyRepository.getLocationFromId(locationId)
            cleaners = location.cleaners
            jobs = location.jobs
        }
    }

    fun onJobValueChange(value: String) {
        job = value.replaceFirstChar {
            it.uppercase()
        }
    }

    fun onCleanerValueChange(value: String) {
        cleaner = value
    }

    fun onJobAddClick() {
        if (job.length > 4) {
            viewModelScope.launch {
                val newJobs = jobs.toMutableList()
                newJobs.add(job)
                location.jobs = newJobs
                job = ""
                companyRepository.updateLocation(location)
                getLocation()
            }
        }
    }

    fun onCleanerAddClick(user: User) {
        if (!cleaners.contains(user)) {
            viewModelScope.launch {
                val newCleaners = cleaners.toMutableList()
                newCleaners.add(user)
                location.cleaners = newCleaners
                cleaner = ""
                companyRepository.updateLocation(location)
                getLocation()
            }
        }
    }

    fun onJobRemoveClick(job: String) {
        viewModelScope.launch {
            val newJobs = jobs.toMutableList()
            newJobs.remove(job)
            location.jobs = newJobs
            companyRepository.updateLocation(location)
            getLocation()
        }
    }

    fun onCleanerRemoveClick(user: User) {
        viewModelScope.launch {
            val newCleaners = cleaners.toMutableList()
            newCleaners.remove(user)
            location.cleaners = newCleaners
            companyRepository.updateLocation(location)
            getLocation()
        }
    }

    fun deleteLocation() {
        viewModelScope.launch {
            companyRepository.deleteLocation(location)
        }
    }
}
