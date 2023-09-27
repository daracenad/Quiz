package cl.daracenad.elearning.exercises.domain.services.course.app

import cl.daracenad.elearning.exercises.data.RepositoryDB
import cl.daracenad.elearning.exercises.data.database.entities.AppParameterEntity
import cl.daracenad.elearning.exercises.domain.model.User
import javax.inject.Inject

class AppTokenUpdateSrv  @Inject constructor(
    private val repository: RepositoryDB
)  {
    suspend operator fun invoke(token:String){
        repository.parameterInsertFormDB(AppParameterEntity(User.TOKEN,token, 0, null))
    }
}