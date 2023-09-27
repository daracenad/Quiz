package cl.daracenad.elearning.exercises.utils

import cl.daracenad.elearning.exercises.utils.http.HeaderInterceptor
import cl.daracenad.elearning.exercises.data.network.model.LoginResponse
import cl.daracenad.elearning.exercises.data.network.model.SchoolResponse
import cl.daracenad.elearning.exercises.data.network.model.test.AnswerResponse
import cl.daracenad.elearning.exercises.data.network.model.school.CourseResponse
import cl.daracenad.elearning.exercises.data.network.model.sys.DonwloadTableResponse
import cl.daracenad.elearning.exercises.data.network.model.test.QuestionResponse
import cl.daracenad.elearning.exercises.data.network.model.test.TestTemplateResponse
import cl.daracenad.elearning.exercises.data.network.model.test.TopicResponse
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServiceBorrar {

    @POST(value="login")
    fun postLogin(
        @Query(value="email") email:String,
        @Query(value="password") password:String
    ): Call<LoginResponse>

    @POST(value="register")
    fun postRequest(
        @Query(value="name") name:String,
        @Query(value="email") email:String,
        @Query(value="password") password:String,
        @Query(value="password_confirmation") password_confirmation:String
    ): Call<SchoolResponse>

    @GET(value="download-table")
    fun downloadTable(
        @Header("Authorization") token: String
    ): Call<DonwloadTableResponse>

    @GET(value="download-topicSerializes")
    fun downloadTopics(
        @Header("Authorization") token: String
    ): Call<TopicResponse>
    @GET(value="download-test-templates")
    fun downloadTestTemplates(
        @Header("Authorization") token: String
    ): Call<TestTemplateResponse>
    @GET(value="download-questionSerializes")
    fun downloadQuestions(
        @Header("Authorization") token: String
    ): Call<QuestionResponse>

    @GET(value="download-answerSerializes")
    fun downloadAnswers(
        @Header("Authorization") token: String
    ): Call<AnswerResponse>

    @GET(value="cours")
    fun getCourses(
        @Header("Authorization") token: String
        ): Call<CourseResponse>

    companion object Factory{
        private const val BASE_URL = "https://buscameaqui.cl/api/"

        fun create(): ApiServiceBorrar {
            val gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getClient())
                .build()

            return retrofit.create(ApiServiceBorrar::class.java)
        }

        private fun getClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
                .addInterceptor(HeaderInterceptor())
                .build()

            return client
        }
    }
}