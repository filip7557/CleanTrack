package hr.ferit.filipcuric.cleantrack.ui.editcompany

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import hr.ferit.filipcuric.cleantrack.data.repository.CompanyRepository
import hr.ferit.filipcuric.cleantrack.model.Company
import kotlinx.coroutines.launch

class EditCompanyViewModel(
    private val companyRepository: CompanyRepository,
    private val navController: NavController,
    private val companyId: String,
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    var imageUri: Uri? by mutableStateOf(Uri.EMPTY)
        private set

    var imageUrl = ""
        private set
    private var managerId = ""

    fun onNameValueChange(value: String) {
        name = value
    }

    private fun uploadCompanyLogo() {
        if(imageUri != null && imageUri != Uri.EMPTY) {
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/cleantrack-8d487.appspot.com/o/images%2F${imageUri!!.pathSegments.last()}.jpg?alt=media&token=df1dcb6e-61e4-44e2-bcc9-06a1e0b86f18"
            viewModelScope.launch {
                companyRepository.uploadCompanyLogo(imageUri!!)
            }
        }
        navController.popBackStack()
    }

    fun onImageSelected(uri: Uri) {
        imageUri = uri
    }

    fun updateCompany() {
        viewModelScope.launch {
            uploadCompanyLogo()
            val company = Company(
                name = name,
                managerId = managerId,
                imageUrl = imageUrl,
            )
            companyRepository.editCompanyById(companyId, company)
        }
    }

    fun getCurrentCompany() {
        viewModelScope.launch {
            val company = companyRepository.getCompanyFromId(companyId)
            name = company.name
            imageUrl = company.imageUrl!!
            managerId = company.managerId!!
        }
    }
}
