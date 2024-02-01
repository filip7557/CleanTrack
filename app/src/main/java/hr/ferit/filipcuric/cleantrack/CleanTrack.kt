package hr.ferit.filipcuric.cleantrack

import android.app.Application
import hr.ferit.filipcuric.cleantrack.data.di.dataModule
import hr.ferit.filipcuric.cleantrack.data.di.databaseModule
import hr.ferit.filipcuric.cleantrack.ui.addworker.di.addWorkerModule
import hr.ferit.filipcuric.cleantrack.ui.companyinfo.di.companyInfoModule
import hr.ferit.filipcuric.cleantrack.ui.createcompany.di.createCompanyModule
import hr.ferit.filipcuric.cleantrack.ui.home.di.homeModule
import hr.ferit.filipcuric.cleantrack.ui.login.di.loginModule
import hr.ferit.filipcuric.cleantrack.ui.main.di.mainModule
import hr.ferit.filipcuric.cleantrack.ui.register.di.registerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CleanTrack : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CleanTrack)
            modules(
                loginModule,
                registerModule,
                dataModule,
                databaseModule,
                mainModule,
                homeModule,
                createCompanyModule,
                companyInfoModule,
                addWorkerModule,
            )
        }
    }
}
