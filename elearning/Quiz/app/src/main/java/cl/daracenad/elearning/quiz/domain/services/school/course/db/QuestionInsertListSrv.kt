package cl.daracenad.elearning.quiz.domain.services.school.course.db

import cl.daracenad.elearning.quiz.data.RepositoryDownloadDB
import cl.daracenad.elearning.quiz.domain.model.test.Question

import cl.daracenad.elearning.quiz.domain.model.test.toEntity
import javax.inject.Inject

class QuestionInsertListSrv @Inject constructor(
    private val repositoryDownloadDB: RepositoryDownloadDB
) {
    suspend operator fun invoke(entityDomains:List<Question>):Boolean{
        try {
            if(entityDomains.isNotEmpty()){
                repositoryDownloadDB.questionInsertList(
                    entityDomains.map {
                        it.toEntity("AC")
                    }
                )
                return true
            }
        }catch(ex:Exception){
            return false
        }
        return false
    }
}