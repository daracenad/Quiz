package cl.daracenad.elearning.quiz.domain.services.course.app

import cl.daracenad.elearning.quiz.data.RepositoryDB
import cl.daracenad.elearning.quiz.data.database.entities.AppParameterEntity
import cl.daracenad.elearning.quiz.domain.model.User
import javax.inject.Inject

class AppTokenUpdateSrv  @Inject constructor(
    private val repository: RepositoryDB
)  {
    suspend operator fun invoke(token:String){
        repository.parameterInsertFormDB(AppParameterEntity(User.TOKEN,token, 0, null))
    }
}