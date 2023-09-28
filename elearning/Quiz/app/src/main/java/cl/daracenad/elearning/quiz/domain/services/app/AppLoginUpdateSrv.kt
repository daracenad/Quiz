package cl.daracenad.elearning.quiz.domain.services.app

import cl.daracenad.elearning.quiz.data.RepositoryDownloadDB
import cl.daracenad.elearning.quiz.data.database.entities.LocalParameterEntity
import cl.daracenad.elearning.quiz.domain.model.User
import javax.inject.Inject

class AppLoginUpdateSrv  @Inject constructor(
    private val repository: RepositoryDownloadDB
)  {
    suspend operator fun invoke(password:String){
        repository.parameterLocalInsertFormDB(LocalParameterEntity(User.PASSWORD,password, 0, null))
    }
}