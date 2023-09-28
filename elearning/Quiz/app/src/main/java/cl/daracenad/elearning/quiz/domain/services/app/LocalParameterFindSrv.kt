package cl.daracenad.elearning.quiz.domain.services.app

import cl.daracenad.elearning.quiz.data.RepositoryDownloadDB
import cl.daracenad.elearning.quiz.domain.model.sys.LocalParameter
import cl.daracenad.elearning.quiz.domain.model.sys.toModel
import javax.inject.Inject

class LocalParameterFindSrv @Inject constructor(private val repository: RepositoryDownloadDB) {
    suspend operator fun invoke(key:String): LocalParameter {
        val parameter = repository.localParameterFindKeyFromDB(key)
        if(parameter != null){
            return parameter.let { it.toModel(true) }
        }
        else{
            return LocalParameter(key,false,"",0, null)
        }
    }



}