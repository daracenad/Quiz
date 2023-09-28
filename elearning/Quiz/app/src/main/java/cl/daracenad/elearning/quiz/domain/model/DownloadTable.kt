package cl.daracenad.elearning.quiz.domain.model


import cl.daracenad.elearning.quiz.data.network.model.download.AnswerResponse
import cl.daracenad.elearning.quiz.data.network.model.download.QuestionResponse
import cl.daracenad.elearning.quiz.data.network.model.download.TestTemplateResponse
import cl.daracenad.elearning.quiz.data.network.model.download.TopicResponse
import cl.daracenad.elearning.quiz.domain.model.test.Answer
import cl.daracenad.elearning.quiz.domain.model.test.Question
import cl.daracenad.elearning.quiz.domain.model.test.TestTemplate
import cl.daracenad.elearning.quiz.domain.model.test.Topic


data class DownloadTable<T>(
    var success:Int,
    var messages: List<String>,
    var version: Int,
    var download: Boolean,
    var lstFields: List<T>
)

fun TopicResponse.toDomain(lst:List<Topic>) = DownloadTable(success,messages,version,download,lst)
fun TestTemplateResponse.testTemplatetoDomain(lst:List<TestTemplate>) = DownloadTable(success,messages,version,download,lst)
fun QuestionResponse.QuestionToDomain(lst:List<Question>) = DownloadTable(success,messages,version,download,lst)
fun AnswerResponse.AnswerToDomain(lst:List<Answer>) = DownloadTable(success,messages,version,download,lst)
