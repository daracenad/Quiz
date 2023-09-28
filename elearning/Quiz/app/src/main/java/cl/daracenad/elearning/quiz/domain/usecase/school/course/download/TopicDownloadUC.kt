package cl.daracenad.elearning.quiz.domain.usecase.school.course.download


import cl.daracenad.elearning.quiz.data.database.entities.TopicEntity
import cl.daracenad.elearning.quiz.domain.services.app.AppTokenGetSrv
import cl.daracenad.elearning.quiz.domain.services.school.course.db.TableVersionGetSrv
import cl.daracenad.elearning.quiz.domain.services.school.course.db.TableVersionPutSrv
import cl.daracenad.elearning.quiz.domain.services.school.course.api.TopicDownloadSrv
import cl.daracenad.elearning.quiz.domain.services.school.course.db.TopicInsertListSrv
import cl.daracenad.elearning.quiz.utils.usercase.DTOResult
import javax.inject.Inject

class TopicDownloadUC @Inject constructor(
    private val appTokenGetSrv: AppTokenGetSrv,
    private val topicDownloadSrv: TopicDownloadSrv,
    private val topicInsertListSrv: TopicInsertListSrv,
    private val tableVersionGetSrv: TableVersionGetSrv,
    private val tableVersionPutSrv: TableVersionPutSrv
) {
    suspend operator fun invoke(schoolId: String, courseId: String): String {
        val token = appTokenGetSrv.invoke()
        var message:String=""
        val version = tableVersionGetSrv.invoke(schoolId, courseId, TopicEntity.TABLE_NAME)

        var dto = topicDownloadSrv.invoke(
            token,
            schoolId,
            courseId,
            version
        )
        when (dto) {
            is DTOResult.Success -> {
                dto.data?.let {
                    topicInsertListSrv.invoke(it.lstFields)
                    message = it.messages.get(0)
                    try{
                        tableVersionPutSrv.invoke(
                            TopicEntity.TABLE_NAME,
                            it.version,
                            schoolId,
                            courseId
                        )
                    }catch(ex: Exception){
                        message= ex.message.toString()
                    }
                }
            }
            else -> {
                message = dto.message.toString()
            }
        }
        return message
    }

}