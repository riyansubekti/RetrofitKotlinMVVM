package riyan.subekti.retrofitkotlinmvvm.ui.auth

import riyan.subekti.retrofitkotlinmvvm.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}