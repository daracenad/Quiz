package cl.daracenad.elearning.exercises.domain


import cl.daracenad.elearning.exercises.data.DownloadTestAPI
import cl.daracenad.elearning.exercises.domain.model.school.Course
import cl.daracenad.elearning.exercises.domain.usecase.user.AppInfoLocalUC
import javax.inject.Inject

class CoursesAllUseCase @Inject constructor(
    private val downloadTestAPI: DownloadTestAPI,
    private val userGetInfoLocalUC: AppInfoLocalUC,

    ){
    suspend operator fun invoke(): List<Course>? {

        val user = userGetInfoLocalUC.invoke()
/*
        var downloadResponse = repository.downloadCoursesFromAPI(
            "Bearer ${user.token}",
            user.schoolId,
            0
        )
*/
        val downloadResponse = downloadTestAPI.downloadCoursesFromAPI(
            "Bearer ",
            "user.schoolId",
            0
        )


        if (downloadResponse != null) {
            if (downloadResponse.success == 0) {
                if (downloadResponse.download) {
                    //insertar los datos.
                    //val tmp = downloadResponse.courses

                    //return  tmp.map {
                    //    it.toDomain()
                    //}
                } else {

                    return null
                }
            }
        } else {


            return null
        }


        return null
    }
}