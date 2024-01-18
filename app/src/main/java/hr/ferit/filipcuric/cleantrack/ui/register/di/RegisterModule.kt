package hr.ferit.filipcuric.cleantrack.ui.register.di

import hr.ferit.filipcuric.cleantrack.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registerModule = module {
    viewModel {
        RegisterViewModel(
            userRepository = get()
        )
    }
}
