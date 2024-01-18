package hr.ferit.filipcuric.cleantrack.data.di.repository

import android.util.Log
import androidx.compose.runtime.collectAsState
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import hr.ferit.filipcuric.cleantrack.data.database.DbLoggedInAccount
import hr.ferit.filipcuric.cleantrack.data.database.LoggedInAccountDao
import hr.ferit.filipcuric.cleantrack.model.User
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

private const val ALGORITHM = "PBKDF2WithHmacSHA512"
private const val ITERATIONS = 120_000
private const val KEY_LENGTH = 256
private const val SECRET = "[B@7ede587"

class UserRepositoryImpl(
    private val loggedInAccountDao: LoggedInAccountDao,
) : UserRepository {
    private val db = Firebase.firestore

    override suspend fun createUser(
        user: User,
    ) {
        db.collection("users").add(user).await()
        loginUser(user)
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
            }.await()
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
            }.await()
        return isEmailAvailable
    }

    override fun generateRandomSalt(): String {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return salt.toString()
    }

    override fun generateHash(password: String, salt: String): String {
        val combinedSalt = "$SECRET".toByteArray()
        val factory: SecretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM)
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), combinedSalt, ITERATIONS, KEY_LENGTH)
        val key: SecretKey = factory.generateSecret(spec)
        val hash: ByteArray = key.encoded
        return hash.toString()
    }

    override suspend fun loginUser(user: User) {
        loggedInAccountDao.delete()
        loggedInAccountDao.insertAccount(
            DbLoggedInAccount(
                username = user.username
            )
        )
    }

    override suspend fun fetchLoggedInUser(): String {
        val account = loggedInAccountDao.account()
        if(account == null)
            return ""
        return loggedInAccountDao.account().username
        }
}
