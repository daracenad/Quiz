package cl.daracenad.elearning.quiz.domain.usecase.school

import cl.daracenad.elearning.quiz.data.RepositoryDownloadDB
import javax.inject.Inject

/**
 *   Verifica si el alumno esta matriculado y
 * si se descargo los cursos de dicha matricular
 */
class MatriculateValidateUC  @Inject constructor(
    private val repositoryDownloadDB: RepositoryDownloadDB
){

    suspend operator fun invoke(){
        //Verificar si esta matriculado
        //repositoryDownloadDB.matriculateFindForId()

    }
}