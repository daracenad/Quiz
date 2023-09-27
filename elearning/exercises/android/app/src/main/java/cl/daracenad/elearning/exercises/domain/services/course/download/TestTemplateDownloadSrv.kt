package cl.daracenad.elearning.exercises.domain.services.course.download


import cl.daracenad.elearning.exercises.data.DownloadTestAPI
import cl.daracenad.elearning.exercises.data.RepositoryDB
import cl.daracenad.elearning.exercises.data.database.entities.AppParameterEntity
import cl.daracenad.elearning.exercises.data.database.entities.TestTemplateEntity

import cl.daracenad.elearning.exercises.domain.services.course.app.ParameterFindSrv
import cl.daracenad.elearning.exercises.domain.model.AppParameter
import cl.daracenad.elearning.exercises.domain.model.test.TestTemplate
import cl.daracenad.elearning.exercises.domain.model.test.toDomain
import cl.daracenad.elearning.exercises.domain.model.test.toEntity
import javax.inject.Inject

class TestTemplateDownloadSrv @Inject constructor(
    private val repository: RepositoryDB,
    private val downloadTestAPI: DownloadTestAPI,
    private val parameterFindSrv: ParameterFindSrv,
)  {
    suspend operator fun invoke(): Int {


        val tokenParameter: AppParameter = parameterFindSrv.invoke("token")
        var versionParameter: AppParameter = parameterFindSrv.invoke("test_templates")
        var schoolIdParameter: AppParameter = parameterFindSrv.invoke("schoolId")

        val token = tokenParameter.valueString
        val version = versionParameter.valueInteger
        val schoolId = schoolIdParameter.valueString

        var downloadResponse = downloadTestAPI.downloadTestTemplatesFromAPI(
            "Bearer ${token}",
            schoolId,
            version
        )

        if (downloadResponse != null) {
            if (downloadResponse.success == 0) {
                if (downloadResponse.download) {
                    //insertar los datos.
                    val tmp = downloadResponse.templates
                    var domain: List<TestTemplate> = tmp.map {
                        it.toDomain()
                    }
                    var entities: List<TestTemplateEntity> = domain.map {
                        it.toEntity()
                    }
                    repository.testTemplateInsertListFromDB(entities)

                    //Actualizar la version
                    val entity = AppParameterEntity("test_templates","",downloadResponse.version, null)

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