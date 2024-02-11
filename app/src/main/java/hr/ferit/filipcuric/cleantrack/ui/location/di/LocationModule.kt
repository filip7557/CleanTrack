package hr.ferit.filipcuric.cleantrack.ui.location.di

import hr.ferit.filipcuric.cleantrack.ui.location.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val locationModule = module {
    viewModel {
        (locationId : String, isUserManager : Boolean) ->
        LocationViewModel(
            userRepository = get(),
            companyRepository = get(),
            locationId = locationId,
            isUserCompanyManager = isUserManager,
        )
    }
}
