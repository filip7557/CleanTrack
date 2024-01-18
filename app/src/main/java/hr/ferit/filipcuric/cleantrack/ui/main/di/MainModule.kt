package hr.ferit.filipcuric.cleantrack.ui.main.di

import hr.ferit.filipcuric.cleantrack.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        MainViewModel(
            userRepository = get()
        )
    }
}
