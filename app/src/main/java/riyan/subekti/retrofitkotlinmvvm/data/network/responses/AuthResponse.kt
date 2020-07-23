package riyan.subekti.retrofitkotlinmvvm.data.network.responses

import riyan.subekti.retrofitkotlinmvvm.data.db.entities.User

data class AuthResponse (
    val isSuccessful: Boolean,
    val message: String?,
    val user: User?
)