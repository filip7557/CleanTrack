package hr.ferit.filipcuric.cleantrack.ui.home.di

import hr.ferit.filipcuric.cleantrack.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(
            userRepository = get(),
            companyRepository = get(),
        )
    }
}
