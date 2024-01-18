package hr.ferit.filipcuric.cleantrack.data.di

import hr.ferit.filipcuric.cleantrack.data.database.LoggedInAccountDao
import hr.ferit.filipcuric.cleantrack.data.di.repository.UserRepository
import hr.ferit.filipcuric.cleantrack.data.di.repository.UserRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> {
        UserRepositoryImpl(
            loggedInAccountDao = get<LoggedInAccountDao>(),
        )
    }
}
