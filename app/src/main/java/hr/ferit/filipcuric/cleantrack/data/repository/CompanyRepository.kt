package hr.ferit.filipcuric.cleantrack.data.repository

import android.net.Uri
import hr.ferit.filipcuric.cleantrack.model.Company

interface CompanyRepository {
    suspend fun fetchUserCompanies(userId: String) : List<Company>
    suspend fun uploadCompanyLogo(uri: Uri)
    suspend fun createCompany(company: Company)
}
