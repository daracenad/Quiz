package cl.daracenad.elearning.exercises.domain.services.course.download


import cl.daracenad.elearning.exercises.data.DownloadTestAPI
import cl.daracenad.elearning.exercises.data.RepositoryDB
import cl.daracenad.elearning.exercises.data.database.entities.AppParameterEntity
import cl.daracenad.elearning.exercises.data.database.entities.TopicEntity
import cl.daracenad.elearning.exercises.domain.services.course.app.ParameterFindSrv
import cl.daracenad.elearning.exercises.domain.model.AppParameter
import cl.daracenad.elearning.exercises.domain.model.test.Topic
import cl.daracenad.elearning.exercises.domain.model.test.toDomain
import cl.daracenad.elearning.exercises.domain.model.test.toEntity
import javax.inject.Inject

class TopicDownloadSrv @Inject constructor(
    private val repository: RepositoryDB,
    private val downloadTestAPI: DownloadTestAPI,
    private val parameterFindSrv: ParameterFindSrv,
)  {
    suspend operator fun invoke(): Int {

        val tokenParameter: AppParameter = parameterFindSrv.invoke("token")
        var versionParameter: AppParameter = parameterFindSrv.invoke("topics")
        var schoolIdParameter: AppParameter = parameterFindSrv.invoke("schoolId")

        val token = tokenParameter.valueString
        val version = versionParameter.valueInteger
        val schoolId = schoolIdParameter.valueString

        var downloadResponse = downloadTestAPI.downloadTopicsFromAPI(
            "Bearer ${token}",
            schoolId,
            version
        )


        if (downloadResponse != null) {
            if (downloadResponse.success == 0) {
                if (downloadResponse.download) {
                    //insertar los datos.
                    val tmp = downloadResponse.topics
                    var domain: List<Topic> = tmp.map {
                        it.toDomain()
                    }
                    var entities: List<TopicEntity> = domain.map {
                        it.toEntity()
                    }
                    repository.topicInsertListFromDB(entities)


                    //Actualizar la version
                    val entity = AppParameterEntity("topics","",downloadResponse.version, null)
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