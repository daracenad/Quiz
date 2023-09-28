package cl.daracenad.elearning.quiz.domain.model.test

import cl.daracenad.elearning.quiz.data.database.entities.AnswerEntity
import cl.daracenad.elearning.quiz.data.network.model.serialize.AnswerSerialize

class Answer(
    val id:String,
    val name:String,
    val description:String,
    val isOk:Boolean,
    val status:String,
    val questionId:String
)
//permite crear un objeto model a partir de un serialize
fun AnswerSerialize.toDomain(status:String) = Answer(id = answerId, name = answerName, description = answerDescription, answerIsOk, status = status ,questionId =  questionId)
fun AnswerEntity.toDomain() = Answer(id, name, description,isOk,status, questionId)
fun Answer.toEntity(status:String) = AnswerEntity(id = id, name = name, description =description,isOk = isOk,status = status, questionId = questionId)
