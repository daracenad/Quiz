package cl.daracenad.elearning.quiz.domain.services.app

import cl.daracenad.elearning.quiz.utils.exception.ModelStudentIdNotFound
import cl.daracenad.elearning.quiz.domain.model.User
import javax.inject.Inject

class AppStudentIdGetSrv @Inject constructor(
    private val localParameterFindSrv: LocalParameterFindSrv
) {

    suspend operator fun invoke(): String {
        val parameter = localParameterFindSrv.invoke(User.STUDENT_ID)
        if (!parameter.exists) {
            throw ModelStudentIdNotFound("Debe volver a realizar un Login")
        }
        return parameter.valueString
    }
}