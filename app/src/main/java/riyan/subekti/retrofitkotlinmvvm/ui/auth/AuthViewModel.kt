package riyan.subekti.retrofitkotlinmvvm.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import riyan.subekti.retrofitkotlinmvvm.data.repositories.UserRepository
import riyan.subekti.retrofitkotlinmvvm.util.ApiExecptions
import riyan.subekti.retrofitkotlinmvvm.util.Coroutines
import riyan.subekti.retrofitkotlinmvvm.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {
    var username: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

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
                val authResponse = repository.userLogin(username!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e: ApiExecptions){
                authListener?.onFailure(e.message!!)
            }catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }

    }

}