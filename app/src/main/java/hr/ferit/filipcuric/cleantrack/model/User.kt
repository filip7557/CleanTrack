package hr.ferit.filipcuric.cleantrack.model

data class User(
    var id: String? = null,
    val username: String,
    val email: String,
    val password: String,
    val firstname: String,
    val lastname: String,
)
