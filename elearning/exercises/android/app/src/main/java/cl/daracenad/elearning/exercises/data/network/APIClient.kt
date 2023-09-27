package cl.daracenad.elearning.exercises.data.network

//import cl.daracenad.elearning.exercises.core.RetrofitHelper

import cl.daracenad.elearning.exercises.data.network.model.test.AnswerResponse
import cl.daracenad.elearning.exercises.data.network.model.school.CourseResponse
import cl.daracenad.elearning.exercises.data.network.model.SchoolResponse
import cl.daracenad.elearning.exercises.data.network.model.school.EnrolledCoursePost
import cl.daracenad.elearning.exercises.data.network.model.sys.DonwloadTableResponse
import cl.daracenad.elearning.exercises.data.network.model.test.QuestionResponse
import cl.daracenad.elearning.exercises.data.network.model.test.TestTemplateResponse
import cl.daracenad.elearning.exercises.data.network.model.test.TopicResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class APIClient @Inject constructor(private val api: IAPIClient) {

    suspend fun downloadTable(token: String): DonwloadTableResponse? {
        return withContext(Dispatchers.IO) {
            val response: Response<DonwloadTableResponse> = api.downloadTable(token)
            response.body()!!
        }
    }

    suspend fun downloadTableAnswers(
        token: String,
        schoolId: String,
        version: Int
    ): AnswerResponse? {
        return withContext(Dispatchers.IO) {
            val response: Response<AnswerResponse> = api.downloadAnswers(token, schoolId, version)
            response.body()!!
        }
    }

    suspend fun downloadTableCurses(
        token: String,
        schoolId: String,
        version: Int
    ): CourseResponse? {
        return withContext(Dispatchers.IO) {
            val response: Response<CourseResponse> = api.downloadCourses(token, schoolId, version)

            response.body()!!
        }
    }

    suspend fun downloadTableTestTemplates(
        token: String,
        schoolId: String,
        version: Int
    ): TestTemplateResponse? {
        return withContext(Dispatchers.IO) {
            val response: Response<TestTemplateResponse> =
                api.downloadTestTemplates(token, schoolId, version)
            response.body()!!
        }
    }

    suspend fun downloadTableTopics(token: String, schoolId: String, version: Int): TopicResponse? {
        return withContext(Dispatchers.IO) {
            val response: Response<TopicResponse> = api.downloadTopics(token, schoolId, version)
            response.body()!!
        }
    }

    suspend fun downloadTableQuestions(
        token: String,
        schoolId: String,
        version: Int
    ): QuestionResponse? {
        return withContext(Dispatchers.IO) {
            val response: Response<QuestionResponse> =
                api.downloadQuestions(token, schoolId, version)
            response.body()!!
        }
    }

    suspend fun profileUpdate(token: String, name: String): ReturnResponse? {
        return withContext(Dispatchers.IO) {

            val response: Response<ReturnResponse> = api.userProfileUpdate(token, name)
            response.body()!!
        }
    }
    /*

        //Api ocupadas
        suspend fun register(
            name: String,
            email: String,
            password: String,
            password_confirmation: String
        ): Response<SchoolResponse>? {
            return withContext(Dispatchers.IO) {
                api.postRequest(
                    name,
                    email,
                    password,
                    password_confirmation
                )
            }
        }

        suspend fun coursesToEnroll(
            token: String,
            studentId: String,
            ids: List<String>
        ): Response<SchoolResponse>? {
            return withContext(Dispatchers.IO) {

                api.coursesToEnroll(token, EnrolledCoursePost(studentId, ids))
            }
        }

        suspend fun profileGET(token: String, studentId: String): Response<SchoolResponse>? {
            return withContext(Dispatchers.IO) {
                var response:Response<SchoolResponse>? = null
                try {
                    response = api.userProfileGET(token, studentId)
                    if (response.isSuccessful && response.body() != null) {

                    } else if (response.body() != null) {

                    } else {

                    }
                }catch(e:Exception){

                }
                response
            }
        }

        suspend fun loginFromAPI(
            email: String,
            password: String
        ): Response<SchoolResponse>? {
            return withContext(Dispatchers.IO) {
                api.postLogin(
                    email,
                    password
                )
            }
        }

     */

}