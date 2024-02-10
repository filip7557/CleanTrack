package hr.ferit.filipcuric.cleantrack.ui.addlocation.di

import hr.ferit.filipcuric.cleantrack.ui.addlocation.AddLocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addLocationModule = module {
    viewModel {
        (companyId : String) ->
        AddLocationViewModel(
            companyRepository = get(),
            companyId = companyId,
        )
    }
}
