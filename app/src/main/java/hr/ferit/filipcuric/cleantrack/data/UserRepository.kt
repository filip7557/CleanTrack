package hr.ferit.filipcuric.cleantrack.data

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import hr.ferit.filipcuric.cleantrack.model.User

class UserRepository {
    private val db = Firebase.firestore

    fun createUser(
        user: User,
    ) {
        db.collection("users").add(user)
    }
}
