package riyan.subekti.retrofitkotlinmvvm.data.repositories

import retrofit2.Response
import riyan.subekti.retrofitkotlinmvvm.data.db.AppDatabase
import riyan.subekti.retrofitkotlinmvvm.data.db.entities.User
import riyan.subekti.retrofitkotlinmvvm.data.network.MyApi
import riyan.subekti.retrofitkotlinmvvm.data.network.SafeApiRequest
import riyan.subekti.retrofitkotlinmvvm.data.network.responses.AuthResponse

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest(){
    suspend fun userLogin(username: String, password: String) : AuthResponse {
        // Ini adalah Bad Practice.
        // Cara ini kurang bagus digunakan
        // Solusinya gunakan depedency injection
        return apiRequest { api.userLogin(username, password) }
    }

    suspend fun saveUser(user: User) = db.getUserdao().upsert(user)

    fun getUser() = db.getUserdao().getuser()
}