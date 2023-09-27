package cl.daracenad.elearning.quiz.domain.services.course.app

import cl.daracenad.elearning.quiz.utils.exception.ModelTokenNotFound
import cl.daracenad.elearning.quiz.domain.model.User
import javax.inject.Inject

class AppTokenGetSrv @Inject constructor(
    private val parameterFindSrv: ParameterFindSrv
) {
    suspend operator fun invoke(): String {
        val parameter = parameterFindSrv.invoke(User.TOKEN)
        if (!parameter.exists) {
            throw ModelTokenNotFound("Debe volver a realizar un Login")
        }
        return "Bearer ${parameter.valueString}"
    }
}