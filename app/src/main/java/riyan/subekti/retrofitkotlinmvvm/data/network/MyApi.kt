package riyan.subekti.retrofitkotlinmvvm.data.network


import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import riyan.subekti.retrofitkotlinmvvm.data.network.responses.AuthResponse

interface MyApi {
    @FormUrlEncoded
    @POST("login.php")
    suspend fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ) : Response<AuthResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MyApi{
            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl("http://192.168.43.193/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}