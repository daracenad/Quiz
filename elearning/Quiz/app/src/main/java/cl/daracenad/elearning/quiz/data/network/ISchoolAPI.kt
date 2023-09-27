package cl.daracenad.elearning.quiz.data.network

import cl.daracenad.elearning.quiz.data.network.model.SchoolResponse
import cl.daracenad.elearning.quiz.data.network.model.school.EnrolledCoursePost
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ISchoolAPI {
    @POST(value="user-matriculate")
    suspend fun matriculate(
        @Header("Authorization") token: String,
        @Body enrolledCoursePost: EnrolledCoursePost
    ): Response<SchoolResponse>

}