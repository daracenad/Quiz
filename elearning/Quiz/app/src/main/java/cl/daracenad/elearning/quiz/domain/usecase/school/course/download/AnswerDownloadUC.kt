package cl.daracenad.elearning.quiz.domain.usecase.school.course.download

import android.util.Log
import cl.daracenad.elearning.quiz.data.database.entities.TopicEntity
import cl.daracenad.elearning.quiz.domain.services.app.AppTokenGetSrv
import cl.daracenad.elearning.quiz.domain.services.school.course.api.AnswerDownloadSrv
import cl.daracenad.elearning.quiz.domain.services.school.course.api.TopicDownloadSrv
import cl.daracenad.elearning.quiz.domain.services.school.course.db.AnswerInsertListSrv
import cl.daracenad.elearning.quiz.domain.services.school.course.db.TableVersionGetSrv
import cl.daracenad.elearning.quiz.domain.services.school.course.db.TableVersionPutSrv
import cl.daracenad.elearning.quiz.domain.services.school.course.db.TopicInsertListSrv
import cl.daracenad.elearning.quiz.utils.usercase.DTOResult
import javax.inject.Inject

class AnswerDownloadUC  @Inject constructor(
    private val appTokenGetSrv: AppTokenGetSrv,
    private val tableVersionGetSrv: TableVersionGetSrv,
    private val tableVersionPutSrv: TableVersionPutSrv,

    private val answerDownloadSrv: AnswerDownloadSrv,
    private val answerInsertListSrv: AnswerInsertListSrv,


    ) {
    suspend operator fun invoke(schoolId: String, courseId: String): String {
        val token = appTokenGetSrv.invoke()
        var message:String=""
        val version = tableVersionGetSrv.invoke(schoolId, courseId, TopicEntity.TABLE_NAME)

        var dto = answerDownloadSrv.invoke(
            token,
            schoolId,
            courseId,
            version
        )

        when (dto) {
            is DTOResult.Success -> {
                dto.data?.let {
                    answerInsertListSrv.invoke(it.lstFields)
                    message = it.messages.get(0)
                    try{
                        if(it.lstFields.size > 0) {
                            tableVersionPutSrv.invoke(
                                TopicEntity.TABLE_NAME,
                                it.version,
                                schoolId,
                                courseId
                            )
                        }else{}
                    }catch(ex: Exception){
                        message= ex.message.toString()
                        Log.e("msgdad", "${message}")
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