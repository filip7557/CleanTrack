package hr.ferit.filipcuric.cleantrack.ui.login.di


import androidx.navigation.NavController
import hr.ferit.filipcuric.cleantrack.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel {
        (navController : NavController) ->
        LoginViewModel(
            userRepository = get(),
            navController = navController,
        )
    }
}
