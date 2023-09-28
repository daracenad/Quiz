package cl.daracenad.elearning.quiz.domain.services.school.course.db

import cl.daracenad.elearning.quiz.data.RepositorySchoolDB

import javax.inject.Inject

class CoursesIntalledSrv@Inject constructor(
    private val repositorySchoolDB: RepositorySchoolDB
) {
    suspend operator fun invoke(id:String){
        repositorySchoolDB.installedCourse(id)
    }
}