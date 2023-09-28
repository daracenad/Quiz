package cl.daracenad.elearning.quiz.domain.services.school.course.api

import cl.daracenad.elearning.quiz.data.network.IDownloadAPIClient
import cl.daracenad.elearning.quiz.data.network.model.download.TopicResponse
import cl.daracenad.elearning.quiz.domain.model.DownloadTable
import cl.daracenad.elearning.quiz.domain.model.test.Topic
import cl.daracenad.elearning.quiz.domain.model.test.toDomain
import cl.daracenad.elearning.quiz.domain.model.toDomain
import cl.daracenad.elearning.quiz.utils.exception.APIDataNotSuccesFull
import cl.daracenad.elearning.quiz.utils.exception.APINotSuccesFull
import cl.daracenad.elearning.quiz.utils.usercase.DTOResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class TopicDownloadSrv @Inject constructor(
    private val api: IDownloadAPIClient
) {
    suspend operator fun invoke(
        token: String,
        schoolId: String,
        courseId: String,
        version: Int
    ): DTOResult<DownloadTable<Topic>> {
        val response = api.downloadTopics(token, schoolId, courseId, version)
        return handleResponse(response)
    }

    private fun handleResponse(response: Response<TopicResponse>?): DTOResult<DownloadTable<Topic>> {
        if (response != null) {
            if (response.isSuccessful && response.body() != null) {
                return if (response.body()!!.success != 0) {
                    DTOResult.Error("${response.body()!!.messages?.get(0)}")
                } else {
                    DTOResult.Success(responseToObjecList(response)!!)
                }
            } else if (response.errorBody() != null) {

                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                return DTOResult.Error(errorObj.getString("message"))
            } else {
                return DTOResult.Error("Something Went Wrong")
            }
        }
        return DTOResult.Error("Sin retorno de información desde el servidor")
    }

    private fun responseToObjecList(response: Response<TopicResponse>): DownloadTable<Topic>? {
        var entityDomain: DownloadTable<Topic>? = null
        if (response.isSuccessful) {
            val body = response.body()
            if (body?.success == 0) {
                if (body.topics != null) {

                    entityDomain = body.toDomain(
                        body.topics.map { entity ->
                            entity.toDomain()
                        })
                }

            } else {
                throw APIDataNotSuccesFull("${body?.messages?.get(0)}")
            }
        } else {
            throw APINotSuccesFull("Registro: No se pudo registrar intente más tarde ${response.message()}")
        }
        return entityDomain


    }

}