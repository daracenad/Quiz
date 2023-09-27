package cl.daracenad.elearning.exercises.domain.services.course.app

import cl.daracenad.elearning.exercises.data.RepositoryDB
import cl.daracenad.elearning.exercises.data.database.entities.AppParameterEntity
import cl.daracenad.elearning.exercises.domain.model.User
import javax.inject.Inject

class AppSchoolIdPutSrv @Inject constructor(
    private val repository: RepositoryDB
)  {
    suspend operator fun invoke(schoolId: String){
        repository.parameterInsertFormDB(AppParameterEntity(User.SCHOOL_ID,schoolId, 0, null))
    }
}