package hr.ferit.filipcuric.cleantrack.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loggedInAccount")
data class DbLoggedInAccount(
    @PrimaryKey val userId: String = "",
)
