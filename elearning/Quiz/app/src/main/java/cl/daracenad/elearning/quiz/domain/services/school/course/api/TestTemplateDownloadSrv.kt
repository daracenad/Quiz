package cl.daracenad.elearning.quiz.domain.services.school.course.api


import cl.daracenad.elearning.quiz.data.network.IDownloadAPIClient
import cl.daracenad.elearning.quiz.data.network.model.download.TestTemplateResponse
import cl.daracenad.elearning.quiz.domain.model.DownloadTable
import cl.daracenad.elearning.quiz.domain.model.test.TestTemplate
import cl.daracenad.elearning.quiz.domain.model.test.toDomain
import cl.daracenad.elearning.quiz.domain.model.testTemplatetoDomain

import cl.daracenad.elearning.quiz.utils.exception.APIDataNotSuccesFull
import cl.daracenad.elearning.quiz.utils.exception.APINotSuccesFull
import cl.daracenad.elearning.quiz.utils.usercase.DTOResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class TestTemplateDownloadSrv@Inject constructor(
    private val api: IDownloadAPIClient
) {
    suspend operator fun invoke(
        token: String,
        schoolId: String,
        courseId: String,
        version: Int
    ): DTOResult<DownloadTable<TestTemplate>> {
        val response = api.downloadTestTemplates(token, schoolId, courseId, version)
        return handleResponse(response)
    }

    private fun handleResponse(response: Response<TestTemplateResponse>?): DTOResult<DownloadTable<TestTemplate>> {
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

    private fun responseToObjecList(response: Response<TestTemplateResponse>): DownloadTable<TestTemplate>? {
        var entityDomain: DownloadTable<TestTemplate>? = null
        if (response.isSuccessful) {
            val body = response.body()
            if (body?.success == 0) {
                if (body.templates != null) {
                    entityDomain = body.testTemplatetoDomain(
                        body.templates.map { entity ->
                            entity.toDomain()
                        }
                    )
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