package hr.ferit.filipcuric.cleantrack.data.repository

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import hr.ferit.filipcuric.cleantrack.data.database.DbLoggedInAccount
import hr.ferit.filipcuric.cleantrack.data.database.LoggedInAccountDao
import hr.ferit.filipcuric.cleantrack.model.User
import hr.ferit.filipcuric.cleantrack.model.Worker
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val loggedInAccountDao: LoggedInAccountDao,
) : UserRepository {
    private val db = Firebase.firestore

    private var loggedInUserId: String = ""

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
        user?.id = userId
        Log.w("USERS_DB", user.toString())
        return user ?: User()
    }

    override suspend fun getUsersByUsername(searchValue: String): List<User> {
        val users = mutableListOf<User>()
        db.collection("users").get()
            .addOnSuccessListener {documents ->
                for (document in documents) {
                    val user = document.toObject(User::class.java)
                    if (searchValue.isNotEmpty() && user.username.startsWith(searchValue)) {
                        user.id = document.id
                        users.add(user)
                    }
                }
            }.await()
        return users
    }

    override suspend fun removeWorkerByUserIdAndCompanyId(userId: String?, companyId: String) {
        val document = db.collection("workers").whereEqualTo("userId", userId!!).whereEqualTo("companyId", companyId).get().await().first()
        db.collection("workers").document(document.id).delete().await()
    }

    override suspend fun addUserToCompanyByCompanyId(userId: String, companyId: String) : Boolean {
        val workers = mutableListOf<Worker>()
        val documents = db.collection("workers").whereEqualTo("userId", userId).get().await()
        for (document in documents) {
            val worker = document.toObject(Worker::class.java)
            workers.add(worker)
        }
        Log.d("WORKERS", workers.toString())
        for (worker in workers) {
            if (worker.companyId == companyId || loggedInUserId == userId)
                return false
        }
        val newWorker = Worker(userId = userId, companyId = companyId)
        db.collection("workers").add(newWorker)
            .addOnSuccessListener { Log.w("WORKER", "Saved worker.") }
            .await()
        return true
    }

    override suspend fun fetchLoggedInUser(): String {
        val account = loggedInAccountDao.account() ?: return ""
        loggedInUserId = account.userId
        return account.userId
    }
}
