package cl.daracenad.elearning.quiz.domain.services.school.course.db

import android.util.Log
import cl.daracenad.elearning.quiz.data.RepositoryDownloadDB
import javax.inject.Inject

class TableVersionGetSrv @Inject constructor(
    private val repository: RepositoryDownloadDB
) {
    suspend operator fun invoke(schoolId:String, courseId:String, table:String): Int {
        try {
            val param = repository.appParameterFindForKey(schoolId, courseId, "table", table)
            if(param!= null)
                return param.version
        }catch(ex:Exception){
            return -1
        }
        return 0
    }
}