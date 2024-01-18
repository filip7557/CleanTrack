package hr.ferit.filipcuric.cleantrack.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbLoggedInAccount::class], version = 1, exportSchema = false)
abstract class CleanTrackDatabase : RoomDatabase() {
    abstract fun loggedInAccountDao(): LoggedInAccountDao
}
