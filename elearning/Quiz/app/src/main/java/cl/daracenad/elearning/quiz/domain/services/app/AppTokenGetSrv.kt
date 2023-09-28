package cl.daracenad.elearning.quiz.domain.services.app

import cl.daracenad.elearning.quiz.utils.exception.ModelTokenNotFound
import cl.daracenad.elearning.quiz.domain.model.User
import javax.inject.Inject

class AppTokenGetSrv @Inject constructor(
    private val localParameterFindSrv: LocalParameterFindSrv
) {
    suspend operator fun invoke(): String {
        val parameter = localParameterFindSrv.invoke(User.TOKEN)
        if (!parameter.exists) {
            throw ModelTokenNotFound("Debe volver a realizar un Login")
        }
        return "Bearer ${parameter.valueString}"
    }
}