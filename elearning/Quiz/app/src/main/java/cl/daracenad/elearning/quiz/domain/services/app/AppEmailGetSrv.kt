package cl.daracenad.elearning.quiz.domain.services.app

import cl.daracenad.elearning.quiz.domain.model.User
import cl.daracenad.elearning.quiz.utils.exception.ModelTokenNotFound
import javax.inject.Inject

class AppEmailGetSrv@Inject constructor(
    private val localParameterFindSrv: LocalParameterFindSrv
){

    suspend operator fun invoke(): String {
        val parameter = localParameterFindSrv.invoke(User.EMAIL)
        if (!parameter.exists) {
            throw ModelTokenNotFound("Debe volver a realizar un Login")
        }
        return parameter.valueString
    }
}