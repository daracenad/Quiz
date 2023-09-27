package cl.daracenad.elearning.quiz.domain.services.course.app

import cl.daracenad.elearning.quiz.data.RepositoryDB
import cl.daracenad.elearning.quiz.data.database.entities.AppParameterEntity
import cl.daracenad.elearning.quiz.domain.model.User
import javax.inject.Inject

class AppLoginUpdateSrv  @Inject constructor(
    private val repository: RepositoryDB
)  {
    suspend operator fun invoke(password:String){
        repository.parameterInsertFormDB(AppParameterEntity(User.PASSWORD,password, 0, null))
    }
}