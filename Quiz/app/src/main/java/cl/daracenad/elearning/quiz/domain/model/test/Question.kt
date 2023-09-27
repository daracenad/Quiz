package cl.daracenad.elearning.quiz.domain.model.test

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import cl.daracenad.elearning.quiz.data.database.entities.CourseEntity
import cl.daracenad.elearning.quiz.data.database.entities.QuestionEntity
import cl.daracenad.elearning.quiz.data.network.model.serialize.CourseSerialize
import cl.daracenad.elearning.quiz.data.network.model.serialize.QuestionSerialize
import org.jetbrains.annotations.NotNull

data class Question (
    val id:String,
    val schoolId:String,
    val courseId:String,
    val topicId:String,
    val testTemplateId:String,
    val name:String,
    val description:String,

)
fun QuestionSerialize.toDomain() =
    Question(id = id, schoolId=schoolId,courseId=courseId,topicId=topicId, testTemplateId=testTemplateId, name = name, description = description)
fun QuestionEntity.toDomain() =
    Question(id = id, schoolId=schoolId,courseId=courseId,topicId=topicId, testTemplateId=testTemplateId, name = name, description = description)
fun Question.toEntity(status:String)  =
    QuestionEntity(id = id, schoolId=schoolId,courseId=courseId,topicId=topicId, testTemplateId=testTemplateId, name = name, description = description, status = status)
