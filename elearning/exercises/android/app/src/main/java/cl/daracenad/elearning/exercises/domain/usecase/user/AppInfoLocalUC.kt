package cl.daracenad.elearning.exercises.domain.usecase.user


import cl.daracenad.elearning.exercises.data.RepositorySchoolDB
import cl.daracenad.elearning.exercises.domain.model.APIConst.Companion.GO_DOWNLOAD_COURSE
import cl.daracenad.elearning.exercises.domain.model.APIConst.Companion.GO_LOGIN
import cl.daracenad.elearning.exercises.domain.model.APIConst.Companion.GO_MATRICULATE
import cl.daracenad.elearning.exercises.domain.model.APIConst.Companion.OK

import cl.daracenad.elearning.exercises.domain.model.AppStatus
import cl.daracenad.elearning.exercises.domain.services.course.app.AppEmailGetSrv
import cl.daracenad.elearning.exercises.domain.services.course.app.AppSchoolIdGetSrv
import cl.daracenad.elearning.exercises.domain.services.course.app.AppStudentIdGetSrv
import cl.daracenad.elearning.exercises.domain.services.course.app.AppTokenGetSrv
import javax.inject.Inject

/**
 * Objetivo: Cheque si existe informacion de:
 * email
 * name
 * schoolId
 * token
 *  en caso contrario devuelve una acción

 */
class AppInfoLocalUC @Inject constructor(
    private val repositorySchoolDB: RepositorySchoolDB,
    private val userSchoolIdGetUC: AppSchoolIdGetSrv,
    private val appEmailGetUC: AppEmailGetSrv,
    private val userStudentIdGetUC: AppStudentIdGetSrv,
    private val userTokenGetUC: AppTokenGetSrv
) {
    suspend operator fun invoke(): AppStatus {

        try {
            userSchoolIdGetUC.invoke()
            appEmailGetUC.invoke()
            userStudentIdGetUC.invoke()
            userTokenGetUC.invoke()
        } catch (ex: Exception) {
            return AppStatus(GO_LOGIN, "Solicitar registar")
        }

        if (!repositorySchoolDB.matriculateExists())
            return AppStatus(GO_MATRICULATE, "Ir a Matricula de cursos")

        if(repositorySchoolDB.isPendingInstallation())
            return AppStatus(GO_DOWNLOAD_COURSE, "Ir a descargar Cursos")

        return AppStatus(OK, "Todo en orden")


    }
}