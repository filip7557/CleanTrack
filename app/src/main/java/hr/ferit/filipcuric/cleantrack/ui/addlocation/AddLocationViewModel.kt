package hr.ferit.filipcuric.cleantrack.ui.addlocation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.ferit.filipcuric.cleantrack.data.repository.CompanyRepository
import hr.ferit.filipcuric.cleantrack.model.Location
import kotlinx.coroutines.launch

class AddLocationViewModel(
    private val companyRepository: CompanyRepository,
    private val companyId: String,
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    var address by mutableStateOf("")
        private set

    var frequency by mutableStateOf("")
        private set

    fun onNameChange(value: String) {
        name = value
    }

    fun onAddressChange(value: String) {
        address = value
    }

    fun onFrequencyChange(value: String) {
        frequency = value
    }

    fun addLocation() {
        viewModelScope.launch {
            val location = Location(
                companyId = companyId,
                name = name,
                address = address,
                frequency = frequency.toInt(),
            )
            companyRepository.createLocation(location)
        }
    }
}
