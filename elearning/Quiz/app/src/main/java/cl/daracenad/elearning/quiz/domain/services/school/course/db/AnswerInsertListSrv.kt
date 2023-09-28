package cl.daracenad.elearning.quiz.domain.services.school.course.db

import cl.daracenad.elearning.quiz.data.RepositoryDownloadDB
import cl.daracenad.elearning.quiz.domain.model.test.Answer

import cl.daracenad.elearning.quiz.domain.model.test.toEntity
import javax.inject.Inject

class AnswerInsertListSrv @Inject constructor(
    private val repositoryDownloadDB: RepositoryDownloadDB
) {
    suspend operator fun invoke(entityDomains:List<Answer>):Boolean{
        try {
            if(entityDomains.isNotEmpty()){
                repositoryDownloadDB.answerInsertList(
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