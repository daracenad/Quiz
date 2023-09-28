package cl.daracenad.elearning.quiz.domain.services.school.course.db

import cl.daracenad.elearning.quiz.data.RepositoryDownloadDB
import cl.daracenad.elearning.quiz.domain.model.test.TestTemplate
import cl.daracenad.elearning.quiz.domain.model.test.toEntity
import javax.inject.Inject

class TestTemplateInsertListSrv @Inject constructor(
    private val repositoryDownloadDB: RepositoryDownloadDB
) {
    suspend operator fun invoke(entityDomains:List<TestTemplate>):Boolean{
        try {
            if(entityDomains.isNotEmpty()){
                repositoryDownloadDB.testTemplateInsertList(
                    entityDomains.map {
                        it.toEntity()
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