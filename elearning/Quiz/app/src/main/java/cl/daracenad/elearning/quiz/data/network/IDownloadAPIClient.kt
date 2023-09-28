package cl.daracenad.elearning.quiz.data.network

import cl.daracenad.elearning.quiz.data.network.model.download.AnswerResponse
import cl.daracenad.elearning.quiz.data.network.model.school.CourseResponse
import cl.daracenad.elearning.quiz.data.network.model.sys.DonwloadTableResponse
import cl.daracenad.elearning.quiz.data.network.model.download.QuestionResponse
import cl.daracenad.elearning.quiz.data.network.model.download.TestTemplateResponse
import cl.daracenad.elearning.quiz.data.network.model.download.TopicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Query

interface IDownloadAPIClient {



    @GET(value="download-table")
    suspend fun downloadTable(
        @Header("Authorization") token: String
    ): Response<DonwloadTableResponse>

    @GET(value="download-answers")
    suspend fun downloadAnswers(
        @Header("Authorization") token: String,
        @Query(value="schoolId")  schoolId:String,
        @Query(value="courseId")  courseId:String,
        @Query(value="version")  version:Int
    ): Response<AnswerResponse>

    suspend @GET(value="download-courses")
    fun downloadCourses(
        @Header("Authorization") token: String,
        @Query(value="schoolId")  schoolId:String,
        @Query(value="version")  version:Int
    ): Response<CourseResponse>

    suspend @GET(value="download-topics")
    fun downloadTopics(
        @Header("Authorization") token: String,
        @Query(value="schoolId")  schoolId:String,
        @Query(value="courseId")  courseId:String,
        @Query(value="version")  version:Int
    ): Response<TopicResponse>

    @GET(value="download-test-templates")
    suspend fun downloadTestTemplates(
        @Header("Authorization") token: String,
        @Query(value="schoolId")  schoolId:String,
        @Query(value="courseId")  courseId:String,
        @Query(value="version")  version:Int
    ): Response<TestTemplateResponse>

    @GET(value="download-questions")
    suspend fun downloadQuestions(
        @Header("Authorization") token: String,
        @Query(value="schoolId")  schoolId:String,
        @Query(value="courseId")  courseId:String,
        @Query(value="version")  version:Int
    ): Response<QuestionResponse>



    @PUT(value="user-profile")
    suspend fun userProfileUpdate(
        @Header("Authorization") token: String,
        @Query(value="name")  name:String
    ): Response<ReturnResponse>

}