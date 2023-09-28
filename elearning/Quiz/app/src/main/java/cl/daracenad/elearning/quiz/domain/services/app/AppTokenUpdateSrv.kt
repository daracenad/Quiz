package cl.daracenad.elearning.quiz.domain.services.app

import cl.daracenad.elearning.quiz.data.RepositoryDownloadDB
import cl.daracenad.elearning.quiz.data.database.entities.LocalParameterEntity
import cl.daracenad.elearning.quiz.domain.model.User
import javax.inject.Inject

class AppTokenUpdateSrv  @Inject constructor(
    private val repository: RepositoryDownloadDB
)  {
    suspend operator fun invoke(token:String){
        repository.parameterLocalInsertFormDB(LocalParameterEntity(User.TOKEN,token, 0, null))
    }
}