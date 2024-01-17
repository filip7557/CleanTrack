package hr.ferit.filipcuric.cleantrack.model

data class Location(
    val id: Int,
    val companyId: String,
    val name: String,
    val address: String,
    val frequency: Int,
)
