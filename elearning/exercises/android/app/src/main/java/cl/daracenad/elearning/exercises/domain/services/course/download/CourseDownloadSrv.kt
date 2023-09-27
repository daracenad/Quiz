package cl.daracenad.elearning.exercises.domain.services.course.download


import cl.daracenad.elearning.exercises.data.DownloadTestAPI
import cl.daracenad.elearning.exercises.data.RepositoryDB
import cl.daracenad.elearning.exercises.data.database.entities.AppParameterEntity
import cl.daracenad.elearning.exercises.domain.services.course.app.ParameterFindSrv
import cl.daracenad.elearning.exercises.domain.model.AppParameter
import javax.inject.Inject

class CourseDownloadSrv @Inject constructor(
    private val repository: RepositoryDB,
    private val downloadTestAPI: DownloadTestAPI,
    private val parameterFindSrv: ParameterFindSrv
) {
    suspend operator fun invoke(): Int {


        val tokenParameter: AppParameter = parameterFindSrv.invoke("token")
        var versionParameter: AppParameter = parameterFindSrv.invoke("courses")
        var schoolIdParameter: AppParameter = parameterFindSrv.invoke("schoolId")

        val token = tokenParameter.valueString
        val version = versionParameter.valueInteger
        val schoolId = schoolIdParameter.valueString

        var downloadResponse = downloadTestAPI.downloadCoursesFromAPI(
            "Bearer ${token}",
            schoolId,
            version
        )


        if (downloadResponse != null) {
            if (downloadResponse.success == 0) {
                if (downloadResponse.download) {
                    //insertar los datos.
                    /*
                    val tmp = downloadResponse.courses

                    var domain: List<Course> = tmp.map {
                        it.toDomain()
                    }

                     */
                    /* comentado por falla debe usarse
                    var entities: List<CourseEntity> = domain.map {
                        it.toEntity()
                    }
                    repository.courseInsertListFromDB(entities)
*/

                    //Actualizar la version
                    val entity = AppParameterEntity("courses","",downloadResponse.version, null)

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