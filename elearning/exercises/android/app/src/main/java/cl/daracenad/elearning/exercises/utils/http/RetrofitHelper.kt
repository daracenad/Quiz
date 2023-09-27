package cl.daracenad.elearning.exercises.utils

import cl.daracenad.elearning.exercises.utils.http.ConnectivityInterceptor
import cl.daracenad.elearning.exercises.utils.http.HeaderInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelperx {
    const val BASE_URL = "https://buscameaqui.cl/api/"
    fun getRetrofit(): Retrofit{
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getClient())
            .build()
    }
    //show Network information in to the logcat
    val intercepter = run{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun getClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(intercepter)
            .addInterceptor(ConnectivityInterceptor())
            /*.connectTimeout(30, TimeUnit.SECONDS) //Backend is really slow
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)*/
            .build()

        return client
    }


}