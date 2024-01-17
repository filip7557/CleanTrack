package hr.ferit.filipcuric.cleantrack.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import hr.ferit.filipcuric.cleantrack.model.User
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val db = Firebase.firestore

    fun createUser(
        user: User,
    ) {
        db.collection("users").add(user)
    }

    fun fetchUser(
        username: String,
    ) : User {
        var user: User = User(null, "", "", "", "", "")
        db.collection("users")
            .whereEqualTo("username", username)
            .get()
            .addOnSuccessListener {  documents ->
                user = documents.first().toObject(User::class.java)
                if(user != null)
                    user.id = documents.first().id
            }
            .addOnFailureListener { exception ->
                Log.w("DB", "Error getting documents: $exception")
            }
        return user
    }

    suspend fun isUsernameAvailable(
        username: String
    ) : Boolean {
        var isUsernameAvailable: Boolean = true
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

    suspend fun isEmailAvailable(
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
}
