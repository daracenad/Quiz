package cl.daracenad.elearning.exercises.domain.usecase.school.course

import cl.daracenad.elearning.exercises.domain.services.course.download.AnswerDownloadSrv
import cl.daracenad.elearning.exercises.domain.services.course.download.CourseDownloadSrv
import cl.daracenad.elearning.exercises.domain.services.course.download.QuestionDownloadSrv
import cl.daracenad.elearning.exercises.domain.services.course.download.TestTemplateDownloadSrv
import cl.daracenad.elearning.exercises.domain.services.course.download.TopicDownloadSrv
import javax.inject.Inject

class CoursesDownloadUC @Inject constructor(
    val courseDownloadSrv: CourseDownloadSrv,
    val topicDownloadSrv: TopicDownloadSrv,
    val testTemplateDownloadSrv: TestTemplateDownloadSrv,
    val questionDownloadSrv: QuestionDownloadSrv,
    val answerDownloadSrv: AnswerDownloadSrv
) {
    suspend operator fun invoke(){
        courseDownloadSrv.invoke()
        topicDownloadSrv.invoke()
        testTemplateDownloadSrv.invoke()
        questionDownloadSrv.invoke()
        answerDownloadSrv.invoke()
    }

}