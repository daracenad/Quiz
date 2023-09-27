package cl.daracenad.elearning.exercises.data.network

import cl.daracenad.elearning.exercises.data.network.model.SchoolResponse
import cl.daracenad.elearning.exercises.data.network.model.school.EnrolledCoursePost
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface IUserAPI {
    //ocupadas
    @POST(value="register")
    suspend fun registerPOST(
        @Query(value="name") name:String,
        @Query(value="email") email:String,
        @Query(value="password") password:String,
        @Query(value="password_confirmation") password_confirmation:String
    ): Response<SchoolResponse>

    @GET(value="user-profile")
    suspend fun userProfileGET(
        @Header("Authorization") token: String,
        @Query(value="student") studentId:String
    ): Response<SchoolResponse>

    @POST(value="login")
    suspend fun loginPOST(
        @Query(value="email") email:String,
        @Query(value="password") password:String
    ): Response<SchoolResponse>

}