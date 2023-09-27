package cl.daracenad.elearning.exercises.domain.services.course.app

import cl.daracenad.elearning.exercises.domain.model.User
import cl.daracenad.elearning.exercises.utils.exception.ModelTokenNotFound
import javax.inject.Inject

class AppSchoolIdGetSrv @Inject constructor(
    private val parameterFindSrv: ParameterFindSrv
){

    suspend operator fun invoke(): String {
        val parameter = parameterFindSrv.invoke(User.SCHOOL_ID)
        if (!parameter.exists) {
            throw ModelTokenNotFound("Debe volver a realizar un Login")
        }
        return parameter.valueString
    }
}