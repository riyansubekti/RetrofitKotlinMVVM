package riyan.subekti.retrofitkotlinmvvm.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import riyan.subekti.retrofitkotlinmvvm.data.repositories.UserRepository

class AuthViewModel : ViewModel() {
    var username: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if (username.isNullOrEmpty() || password.isNullOrEmpty()){
            //Failure
            authListener?.onFailure("Invalid email or password")
            return
        }
        // Tanda Seru 2 itu maksudnya operator not null
        // Ini Bad Practice karena tidak menggunakan depedency injection
        val loginResponse = UserRepository().userLogin(username!!, password!!)
        authListener?.onSuccess(loginResponse)
    }

}