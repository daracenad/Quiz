package cl.daracenad.elearning.quiz.domain.services.course.app

import cl.daracenad.elearning.quiz.utils.exception.ModelStudentIdNotFound
import cl.daracenad.elearning.quiz.domain.model.User
import javax.inject.Inject

class AppStudentIdGetSrv @Inject constructor(
    private val parameterFindSrv: ParameterFindSrv
) {

    suspend operator fun invoke(): String {
        val parameter = parameterFindSrv.invoke(User.STUDENT_ID)
        if (!parameter.exists) {
            throw ModelStudentIdNotFound("Debe volver a realizar un Login")
        }
        return parameter.valueString
    }
}