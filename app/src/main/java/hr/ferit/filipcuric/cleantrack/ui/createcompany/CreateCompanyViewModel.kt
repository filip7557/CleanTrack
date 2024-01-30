package hr.ferit.filipcuric.cleantrack.ui.createcompany

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import hr.ferit.filipcuric.cleantrack.data.repository.CompanyRepository
import hr.ferit.filipcuric.cleantrack.data.repository.UserRepository

class CreateCompanyViewModel(
    private val userRepository: UserRepository,
    private val companyRepository: CompanyRepository,
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    fun onNameValueChange(value: String) {
        name = value
    }
}
