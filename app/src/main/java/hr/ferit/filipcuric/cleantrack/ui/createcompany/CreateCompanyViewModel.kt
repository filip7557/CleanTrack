package hr.ferit.filipcuric.cleantrack.ui.createcompany

import android.net.Uri
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
import hr.ferit.filipcuric.cleantrack.navigation.HOME_ROUTE
import hr.ferit.filipcuric.cleantrack.navigation.NavigationItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateCompanyViewModel(
    private val userRepository: UserRepository,
    private val companyRepository: CompanyRepository,
    private val navController: NavController,
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    var imageUri: Uri? by mutableStateOf(Uri.EMPTY)
        private set

    private var imageUrl = ""

    fun onNameValueChange(value: String) {
        name = value
    }

    private fun uploadCompanyLogo() {
            if(imageUri == null || imageUri == Uri.EMPTY)
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/cleantrack-8d487.appspot.com/o/images%2FAsset2_grande.webp?alt=media&token=f0be358d-ef65-49a1-acc2-4d9d2df34296"
            else {
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/cleantrack-8d487.appspot.com/o/images%2F${imageUri!!.pathSegments.last()}.jpg?alt=media&token=df1dcb6e-61e4-44e2-bcc9-06a1e0b86f18"
                viewModelScope.launch {
                    companyRepository.uploadCompanyLogo(imageUri!!)
                    navController.navigate(HOME_ROUTE) {
                        popUpTo(NavigationItem.CreateCompanyDestination.route) {
                            inclusive = true
                        }
                    }
                }
            }
    }

    fun onImageSelected(uri: Uri) {
        imageUri = uri
    }

    fun createCompany() {
        viewModelScope.launch(Dispatchers.IO) {
            uploadCompanyLogo()
            Log.w("LOGO", "$imageUrl | $imageUri")
            val company = Company(
                name = name,
                imageUrl = imageUrl,
                managerId = userRepository.fetchLoggedInUser()
            )
            companyRepository.createCompany(company)
            //navigate to details screen of created company
        }
    }
}
