package cl.daracenad.elearning.exercises.domain.services.course.app

import cl.daracenad.elearning.exercises.data.RepositoryDB
import cl.daracenad.elearning.exercises.data.database.entities.AppParameterEntity
import cl.daracenad.elearning.exercises.domain.model.User
import javax.inject.Inject

class AppStudentIdPutSrv @Inject constructor(
    private val repository: RepositoryDB
)  {
    suspend operator fun invoke(studentId:String){
        repository.parameterInsertFormDB(AppParameterEntity(User.STUDENT_ID,studentId, 0, null))
    }
}