package hr.ferit.filipcuric.cleantrack.ui.createcompany.di

import androidx.navigation.NavController
import hr.ferit.filipcuric.cleantrack.ui.createcompany.CreateCompanyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createCompanyModule = module {
    viewModel {
        (navController : NavController) ->
        CreateCompanyViewModel(
            userRepository = get(),
            companyRepository = get(),
            navController = navController,
        )
    }
}
