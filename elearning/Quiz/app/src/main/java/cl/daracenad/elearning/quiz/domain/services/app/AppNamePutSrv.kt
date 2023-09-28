package cl.daracenad.elearning.quiz.domain.services.app

import cl.daracenad.elearning.quiz.data.RepositoryDownloadDB
import cl.daracenad.elearning.quiz.data.database.entities.LocalParameterEntity
import cl.daracenad.elearning.quiz.domain.model.User
import javax.inject.Inject

class AppNamePutSrv @Inject constructor(
    private val repository: RepositoryDownloadDB
)  {
    suspend operator fun invoke(name:String){
        repository.parameterLocalInsertFormDB(LocalParameterEntity(User.NAME,name,0,null ))
    }
}