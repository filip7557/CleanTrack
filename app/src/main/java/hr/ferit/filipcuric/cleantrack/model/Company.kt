package hr.ferit.filipcuric.cleantrack.model

data class Company(
    var id: String? = null,
    val name: String = "",
    val managerId: String? = null,
    val imageUrl: String? = null,
    val workers: List<Worker> = mutableListOf(),
)
