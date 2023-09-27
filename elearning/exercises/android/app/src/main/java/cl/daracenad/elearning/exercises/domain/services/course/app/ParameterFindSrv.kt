package cl.daracenad.elearning.exercises.domain.services.course.app

import cl.daracenad.elearning.exercises.data.RepositoryDB
import cl.daracenad.elearning.exercises.domain.model.AppParameter
import cl.daracenad.elearning.exercises.domain.model.toModel
import javax.inject.Inject

class ParameterFindSrv @Inject constructor(private val repository: RepositoryDB) {
    suspend operator fun invoke(key:String): AppParameter {
        val parameter = repository.parameterFindKeyFromDB(key)
        if(parameter != null){
            return parameter.let { it.toModel(true) }
        }
        else{
            return AppParameter(key,false,"",0, null)
        }
    }



}