package hr.ferit.filipcuric.cleantrack.data.repository

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import hr.ferit.filipcuric.cleantrack.data.database.DbLoggedInAccount
import hr.ferit.filipcuric.cleantrack.data.database.LoggedInAccountDao
import hr.ferit.filipcuric.cleantrack.model.User
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val loggedInAccountDao: LoggedInAccountDao,
) : UserRepository {
    private val db = Firebase.firestore

    override suspend fun createUser(
        user: User,
    ) {
        db.collection("users").add(user).await()
        val document = db.collection("users").whereEqualTo("username", user.username).get().await().documents.first()
        saveLoggedInUser(document.id)
    }

    override suspend fun saveLoggedInUser(userId: String) {
        loggedInAccountDao.delete()
        loggedInAccountDao.insertAccount(
            DbLoggedInAccount(
                userId = userId
            )
        )
    }

    override suspend fun signOutUser() {
        loggedInAccountDao.delete()
    }

    override suspend fun fetchUser(
        username: String,
    ) : User {
        var user = User(null, "", "", "", "", "")
        db.collection("users")
            .whereEqualTo("username", username)
            .get()
            .addOnSuccessListener {  documents ->
                user.id = documents.first().id
                user = documents.first().toObject(User::class.java) //TODO Fix this being too late and giving empty user
            }
            .addOnFailureListener { exception ->
                Log.w("DB", "Error getting documents: $exception")
            }.await()
        Log.d("DB", user.password)
        return user
    }

    override suspend fun isUsernameAvailable(
        username: String
    ) : Boolean {
        var isUsernameAvailable = true
        db.collection("users")
            .whereEqualTo("username", username)
            .get()
            .addOnSuccessListener {  documents ->
                if(!documents.isEmpty)
                    isUsernameAvailable = false
            }
            .addOnFailureListener { exception ->
                Log.w("DB", "Error getting documents: $exception")
            }
            .await()
        return isUsernameAvailable
    }

    override suspend fun isEmailAvailable(
        email: String
    ) : Boolean {
        var isEmailAvailable = true
        db.collection("users")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener {  documents ->
                if(!documents.isEmpty)
                    isEmailAvailable = false
            }
            .addOnFailureListener { exception ->
                Log.w("DB", "Error getting documents: $exception")
            }
            .await()
        return isEmailAvailable
    }

    override suspend fun loginUser(username: String, password: String) {
        val documents = db.collection("users")
            .whereEqualTo("username", username)
            .get()
            .await()
        if (!documents.isEmpty) {
            val user = documents.first().toObject(User::class.java)
            if (user.password == password) {
                saveLoggedInUser(documents.first().id)
            }
        }
    }

    override suspend fun getUserFromId(userId: String?): User {
        val user = db.collection("users").document(userId  ?: "xxxxxxxxxxxxxxxxxxxx").get().await().toObject(User::class.java)
        Log.w("USERS_DB", user.toString())
        return user ?: User()
    }

    override suspend fun fetchLoggedInUser(): String {
        val account = loggedInAccountDao.account() ?: return ""
        return account.userId
    }

    override suspend fun restoreLoggedInUser(): User? {
        val userId = fetchLoggedInUser()
        Log.d("ROOM", userId)
        if (userId == "")
            return null
        return fetchUser(userId)
    }
}
