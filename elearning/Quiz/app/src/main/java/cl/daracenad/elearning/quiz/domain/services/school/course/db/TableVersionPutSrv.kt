package cl.daracenad.elearning.quiz.domain.services.school.course.db

import android.util.Log
import cl.daracenad.elearning.quiz.data.RepositoryDownloadDB
import cl.daracenad.elearning.quiz.data.database.entities.AppParameterEntity
import javax.inject.Inject

class TableVersionPutSrv  @Inject constructor(
    private val repository: RepositoryDownloadDB
) {
    suspend operator fun invoke(entity:String, version:Int, schoolId:String, courseId:String): Int {
        try {
            repository.appParameterInsert(AppParameterEntity(1,schoolId,courseId,
            "table",entity,entity,version,"download","AC"))
        }catch(ex:Exception){
            Log.e("msgdad TableVersionGetSrv","${ex.message}")
        }
        return -1
    }
}