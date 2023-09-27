package cl.daracenad.elearning.quiz.domain.services.course.app

import cl.daracenad.elearning.quiz.data.RepositoryDB
import cl.daracenad.elearning.quiz.data.database.entities.AppParameterEntity
import cl.daracenad.elearning.quiz.domain.model.User
import javax.inject.Inject

class AppEmailPutSrv @Inject constructor(
    private val repository: RepositoryDB
)  {
    suspend operator fun invoke(email:String){
        repository.parameterInsertFormDB(AppParameterEntity(User.EMAIL,email,0,null ))
    }
}