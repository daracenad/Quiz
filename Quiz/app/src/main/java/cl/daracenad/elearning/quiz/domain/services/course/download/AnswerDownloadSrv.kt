package cl.daracenad.elearning.quiz.domain.services.course.download

import cl.daracenad.elearning.quiz.data.DownloadTestAPI
import cl.daracenad.elearning.quiz.data.RepositoryDB
import cl.daracenad.elearning.quiz.data.database.entities.AnswerEntity
import cl.daracenad.elearning.quiz.data.database.entities.AppParameterEntity
import cl.daracenad.elearning.quiz.domain.services.course.app.ParameterFindSrv
import cl.daracenad.elearning.quiz.domain.model.AppParameter
import cl.daracenad.elearning.quiz.domain.model.test.Answer
import cl.daracenad.elearning.quiz.domain.model.test.toDomain
import cl.daracenad.elearning.quiz.domain.model.test.toEntity
import javax.inject.Inject

class AnswerDownloadSrv @Inject constructor(
    private val repository: RepositoryDB,
    private val downloadTestAPI: DownloadTestAPI,
    private val parameterFindSrv: ParameterFindSrv,
    )  {
    suspend operator fun invoke(): Int {


        val tokenParameter: AppParameter = parameterFindSrv.invoke("token")
        var versionParameter: AppParameter = parameterFindSrv.invoke("answers")
        var schoolIdParameter: AppParameter = parameterFindSrv.invoke("schoolId")

        val token = tokenParameter.valueString
        val version = versionParameter.valueInteger
        val schoolId = schoolIdParameter.valueString

        var downloadResponse = downloadTestAPI.downloadAnswersFromAPI(
            "Bearer ${token}",
            schoolId,
            version
        )


        if (downloadResponse != null) {
            if (downloadResponse.success == 0) {
                if (downloadResponse.download) {
                    //insertar los datos.
                    val tmp = downloadResponse.answers

                    var domain: List<Answer> = tmp.map {
                        it.toDomain()
                    }
                    var entities: List<AnswerEntity> = domain.map {
                        it.toEntity()
                    }

                    repository.answerInsertListFromDB(entities)


                    //Actualizar la version
                    val entity = AppParameterEntity("answers","",downloadResponse.version,null)

                    repository.parameterInsertFormDB(entity)
                } else {

                    return 1 //No es necesario actualizar
                }
            }
        } else {
            return 2
        }


        return 0
    }
}