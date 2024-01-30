package hr.ferit.filipcuric.cleantrack.data.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import hr.ferit.filipcuric.cleantrack.model.Company
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
            /*.addOnSuccessListener {
            Log.w("PICTURE", "Uploaded successfully.")
        }
            .addOnFailureListener{
                Log.w("PICTURE", "Error uploading picture: $it")
            }*/
    }

    override suspend fun createCompany(company: Company) {
        db.collection("companies").add(company).await()
    }
}
