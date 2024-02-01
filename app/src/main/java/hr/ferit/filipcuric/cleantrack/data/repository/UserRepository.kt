package hr.ferit.filipcuric.cleantrack.data.repository

import hr.ferit.filipcuric.cleantrack.model.User

interface UserRepository {
    suspend fun createUser(user: User)
    suspend fun fetchUser(username: String) : User
    suspend fun isUsernameAvailable(username: String) : Boolean
    suspend fun isEmailAvailable(email: String) : Boolean
    suspend fun  fetchLoggedInUser() : String
    suspend fun loginUser(username: String, password: String)
    suspend fun saveLoggedInUser(userId: String)
    suspend fun signOutUser()
    suspend fun getUserFromId(userId: String?): User
    suspend fun getUsersByUsername(searchValue: String) : List<User>
    suspend fun addUserToCompanyByCompanyId(userId: String, companyId: String) : Boolean
    suspend fun removeWorkerByUserIdAndCompanyId(userId: String?, companyId: String)
}
