package hr.ferit.filipcuric.cleantrack.data.repository

import hr.ferit.filipcuric.cleantrack.model.Company

interface CompanyRepository {
    suspend fun fetchUserCompanies(userId: String) : List<Company>
}
