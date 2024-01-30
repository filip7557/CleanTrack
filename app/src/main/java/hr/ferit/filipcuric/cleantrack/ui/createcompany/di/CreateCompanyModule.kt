package hr.ferit.filipcuric.cleantrack.ui.createcompany.di

import hr.ferit.filipcuric.cleantrack.ui.createcompany.CreateCompanyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createCompanyModule = module {
    viewModel {
        CreateCompanyViewModel(
            userRepository = get(),
            companyRepository = get(),
        )
    }
}
