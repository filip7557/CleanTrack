package hr.ferit.filipcuric.cleantrack.ui.editcompany.di

import androidx.navigation.NavController
import hr.ferit.filipcuric.cleantrack.ui.editcompany.EditCompanyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val editCompanyModule = module {
    viewModel {
        (navController : NavController, companyId : String) ->
        EditCompanyViewModel(
            companyRepository = get(),
            navController = navController,
            companyId = companyId,
        )
    }
}
