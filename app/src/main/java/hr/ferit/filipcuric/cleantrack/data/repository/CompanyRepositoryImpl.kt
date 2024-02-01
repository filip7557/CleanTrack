package hr.ferit.filipcuric.cleantrack.data.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import hr.ferit.filipcuric.cleantrack.model.Company
import hr.ferit.filipcuric.cleantrack.model.Location
import hr.ferit.filipcuric.cleantrack.model.User
import hr.ferit.filipcuric.cleantrack.model.Worker
import kotlinx.coroutines.tasks.await

class CompanyRepositoryImpl: CompanyRepository {
    private val db = Firebase.firestore
    private val storageRef = Firebase.storage.reference

    override suspend fun fetchUserCompanies(userId: String): List<Company> {
        val companies = mutableListOf<Company>()
        //Get companies whose manager is given user
        var documents = db.collection("companies").whereEqualTo("managerId", userId).get().await()
        for (document in documents) {
            val company = document.toObject(Company::class.java)
            company.id = document.id
            companies.add(company)
        }
        //Get companies where given user works
         documents = db.collection("workers").whereEqualTo("userId", userId).get().await()
        for (document in documents) {
            val worker = document.toObject(Worker::class.java)
            val company = db.collection("companies").document(worker.companyId).get().await().toObject(Company::class.java)
            company?.id = worker.companyId
            companies.add(company!!)
        }
        return companies
    }

    override suspend fun uploadCompanyLogo(uri: Uri) {
        val imageRef = storageRef.child("images/${uri.pathSegments.last()}.jpg")
        val uploadTask = imageRef.putFile(uri)
        uploadTask.await()
    }

    override suspend fun createCompany(company: Company) {
        db.collection("companies").add(company).await()
    }

    override suspend fun getCompanyFromId(companyId: String) : Company {
        val company = db.collection("companies").document(companyId).get().await().toObject(Company::class.java)
        Log.w("COMPANY_DB", company.toString())
        return company ?: Company()
    }

    override suspend fun getLocationsFromCompanyId(companyId: String): List<Location> {
        val locations = mutableListOf<Location>()
        Log.d("LOCATIONS", "GETTING LOCATIONS FOR $companyId")
        val documents = db.collection("locations").whereEqualTo("companyId", companyId).get().await()
        for (document in documents) {
            val location = document.toObject(Location::class.java)
            location.id = document.id
            Log.w("LOCATIONS", location.toString())
            locations.add(location)
        }
        Log.w("LOCATIONS REP", locations.toString())
        return locations
    }

    override suspend fun getWorkersFromCompanyId(companyId: String): List<User> {
        val users = mutableListOf<User>()
        val workers = db.collection("workers").whereEqualTo("companyId", companyId).get().await().toObjects(Worker::class.java)
        for (worker in workers) {
            val user = db.collection("users").document(worker.userId).get().await().toObject(User::class.java)
            if (user != null) {
                user.id = worker.userId
            }
            if (user != null) {
                users.add(user)
            }
        }
        return users
    }

    override suspend fun editCompanyById(
        companyId: String,
        company: Company,
    ) {
        db.collection("companies").document(companyId).set(company).await()
    }

    override suspend fun deleteCompanyById(id: String?) {
        db.collection("companies").document(id!!).delete().await()
    }
}
