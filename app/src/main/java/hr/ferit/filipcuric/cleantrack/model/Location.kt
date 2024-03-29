package hr.ferit.filipcuric.cleantrack.model

data class Location(
    var id: String = "",
    val companyId: String = "",
    val name: String = "",
    val address: String = "",
    val frequency: Int = 0,
    var cleaners: MutableList<User> = mutableListOf(),
    var jobs: MutableList<String> = mutableListOf(),
)
