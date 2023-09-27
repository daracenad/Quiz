package cl.daracenad.elearning.quiz.data

import cl.daracenad.elearning.quiz.data.network.APIClient
import cl.daracenad.elearning.quiz.data.network.ReturnResponse
import cl.daracenad.elearning.quiz.data.network.model.school.CourseResponse
import cl.daracenad.elearning.quiz.data.network.model.test.AnswerResponse
import cl.daracenad.elearning.quiz.data.network.model.test.QuestionResponse
import cl.daracenad.elearning.quiz.data.network.model.test.TestTemplateResponse
import cl.daracenad.elearning.quiz.data.network.model.test.TopicResponse
import javax.inject.Inject

class DownloadTestAPI  @Inject constructor(
    private val api: APIClient,
) {
    suspend fun downloadAnswersFromAPI(token: String, schoolId:String, version:Int): AnswerResponse? {
        val response = api.downloadTableAnswers(token, schoolId, version)
        return response
    }
    suspend fun downloadCoursesFromAPI(token: String, schoolId:String, version:Int): CourseResponse? {
        val response = api.downloadTableCurses(token, schoolId, version)
        return response
    }
    suspend fun downloadTestTemplatesFromAPI(token: String, schoolId:String,version:Int): TestTemplateResponse? {
        val response = api.downloadTableTestTemplates(token, schoolId, version)
        return response
    }
    suspend fun downloadTopicsFromAPI(token: String, schoolId:String, version:Int): TopicResponse? {
        val response = api.downloadTableTopics(token, schoolId, version)
        return response
    }
    suspend fun downloadQuestionsFromAPI(token: String, schoolId:String, version:Int): QuestionResponse? {
        val response = api.downloadTableQuestions(token, schoolId, version)
        return response
    }
    suspend fun profileUpdate(token: String, name:String): ReturnResponse? {
        val response = api.profileUpdate(token, name)
        return response
    }

}