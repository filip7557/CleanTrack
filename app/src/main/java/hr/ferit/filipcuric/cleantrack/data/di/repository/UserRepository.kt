package hr.ferit.filipcuric.cleantrack.data.di.repository

import hr.ferit.filipcuric.cleantrack.model.User

interface UserRepository {
    suspend fun createUser(user: User)

    suspend fun fetchUser(username: String) : User

    suspend fun isUsernameAvailable(username: String) : Boolean

    suspend fun isEmailAvailable(email: String) : Boolean

    fun generateRandomSalt(): String

    fun generateHash(password: String, salt: String) : String
}
