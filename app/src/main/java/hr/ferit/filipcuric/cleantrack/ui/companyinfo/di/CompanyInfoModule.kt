package hr.ferit.filipcuric.cleantrack.ui.companyinfo.di

import androidx.navigation.NavController
import hr.ferit.filipcuric.cleantrack.ui.companyinfo.CompanyInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val companyInfoModule = module {
    viewModel {
        (navController : NavController, companyId : String) ->
        CompanyInfoViewModel(
            userRepository = get(),
            companyRepository = get(),
            navController = navController,
            companyId = companyId,
        )
    }
}
