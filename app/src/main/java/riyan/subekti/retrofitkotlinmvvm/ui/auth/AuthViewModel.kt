package riyan.subekti.retrofitkotlinmvvm.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import riyan.subekti.retrofitkotlinmvvm.data.repositories.UserRepository
import riyan.subekti.retrofitkotlinmvvm.util.ApiExecptions
import riyan.subekti.retrofitkotlinmvvm.util.Coroutines

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
        Coroutines.main {
            try {
                val authResponse = UserRepository().userLogin(username!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e: ApiExecptions){
                authListener?.onFailure(e.message!!)
            }
        }

    }

}