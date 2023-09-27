package cl.daracenad.elearning.exercises.domain.usecase.school

import cl.daracenad.elearning.exercises.data.RepositoryDB
import javax.inject.Inject

/**
 *   Verifica si el alumno esta matriculado y
 * si se descargo los cursos de dicha matricular
 */
class MatriculateValidateUC  @Inject constructor(
    private val repositoryDB: RepositoryDB
){

    suspend operator fun invoke(){
        //Verificar si esta matriculado
        //repositoryDB.matriculateFindForId()

    }
}