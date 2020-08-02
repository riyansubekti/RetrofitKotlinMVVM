package riyan.subekti.retrofitkotlinmvvm.data.network

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import riyan.subekti.retrofitkotlinmvvm.util.ApiExecptions
import java.lang.StringBuilder

abstract class SafeApiRequest {
    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T{
        val response = call.invoke()

        if(response.isSuccessful){
            return response.body()!!
        }else{
            //Error ini merupakan kode page 401
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                }catch (e: JSONException){ }
                message.append("\n")
            }
            message.append("Error code : ${response.code()}")
            throw ApiExecptions(message.toString())
        }
    }
}