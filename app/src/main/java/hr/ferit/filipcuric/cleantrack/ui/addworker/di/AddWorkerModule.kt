package hr.ferit.filipcuric.cleantrack.ui.addworker.di

import androidx.navigation.NavController
import hr.ferit.filipcuric.cleantrack.ui.addworker.AddWorkerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addWorkerModule = module {
    viewModel {
        (navController : NavController, companyId : String) ->
        AddWorkerViewModel(
            userRepository = get(),
            navController = navController,
            companyId = companyId
        )
    }
}
