package hr.ferit.filipcuric.cleantrack.data.repository

import android.net.Uri
import hr.ferit.filipcuric.cleantrack.model.Company
import hr.ferit.filipcuric.cleantrack.model.Location
import hr.ferit.filipcuric.cleantrack.model.User

interface CompanyRepository {
    suspend fun fetchUserCompanies(userId: String) : List<Company>
    suspend fun uploadCompanyLogo(uri: Uri)
    suspend fun createCompany(company: Company)
    suspend fun getCompanyFromId(companyId: String): Company
    suspend fun deleteCompanyById(id: String?)
    suspend fun getLocationsFromCompanyId(companyId: String): List<Location>
    suspend fun getWorkersFromCompanyId(companyId: String): List<User>
    suspend fun editCompanyById(companyId: String, company: Company)
    suspend fun createLocation(location: Location)
}
