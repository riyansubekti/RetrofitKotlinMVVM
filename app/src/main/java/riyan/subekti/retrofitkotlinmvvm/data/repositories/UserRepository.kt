package riyan.subekti.retrofitkotlinmvvm.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import riyan.subekti.retrofitkotlinmvvm.data.network.MyApi

class UserRepository {
    fun userLogin(username: String, password: String) : LiveData<String> {
        val loginResponse = MutableLiveData<String>()

        // Ini adalah Bad Practice.
        // Cara ini kurang bagus digunakan
        // Solusinya gunakan depedency injection
        MyApi().userLogin(username, password)
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful){
                        loginResponse.value = response.body()?.string()
                    }else{
                        loginResponse.value = response.errorBody()?.string()
                    }
                }

            })
        return loginResponse
    }
}