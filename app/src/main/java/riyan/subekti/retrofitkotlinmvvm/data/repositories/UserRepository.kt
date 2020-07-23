package riyan.subekti.retrofitkotlinmvvm.data.repositories

import retrofit2.Response
import riyan.subekti.retrofitkotlinmvvm.data.network.MyApi
import riyan.subekti.retrofitkotlinmvvm.data.network.responses.AuthResponse

class UserRepository {
    suspend fun userLogin(username: String, password: String) : Response<AuthResponse> {
        // Ini adalah Bad Practice.
        // Cara ini kurang bagus digunakan
        // Solusinya gunakan depedency injection
        return MyApi().userLogin(username, password)
    }
}