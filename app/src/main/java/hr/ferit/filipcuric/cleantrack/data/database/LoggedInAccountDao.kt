package hr.ferit.filipcuric.cleantrack.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LoggedInAccountDao {
     @Query("SELECT * FROM loggedInAccount")
     fun account(): DbLoggedInAccount

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertAccount(account: DbLoggedInAccount)

     @Query("DELETE FROM loggedInAccount")
     suspend fun delete()
}
