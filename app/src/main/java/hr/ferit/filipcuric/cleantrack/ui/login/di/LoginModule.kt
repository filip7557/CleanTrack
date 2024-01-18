package hr.ferit.filipcuric.cleantrack.ui.login.di


import hr.ferit.filipcuric.cleantrack.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel {
        LoginViewModel(
            userRepository = get(),
        )
    }
}
