package hr.ferit.filipcuric.cleantrack.data.di

import androidx.room.Room
import hr.ferit.filipcuric.cleantrack.data.database.CleanTrackDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val APP_DATABASE_NAME = "cleantrack_databse.db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            CleanTrackDatabase::class.java,
            APP_DATABASE_NAME,
        ).build()
    }
    fun provideLoggedInAccountDao(database: CleanTrackDatabase) = database.loggedInAccountDao()
    single { provideLoggedInAccountDao(get<CleanTrackDatabase>()) }
}
