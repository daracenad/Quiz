package cl.daracenad.elearning.exercises.domain.model.test

import cl.daracenad.elearning.exercises.data.database.entities.AnswerEntity
import cl.daracenad.elearning.exercises.data.network.model.serialize.AnswerSerialize

class Answer(
    val id:String,
    val name:String,
    val description:String,
    val isOk:Boolean,
    val status:String,
    val questionId:String
)
//permite crear un objeto model a partir de un serialize
fun AnswerSerialize.toDomain() = Answer(id = answerId, name = answerName, description = answerDescription, answerIsOk, status = "AC" ,questionId =  questionId)
fun AnswerEntity.toDomain() = Answer(id, name, description,isOk,status, questionId)
fun Answer.toEntity() = AnswerEntity(id, name, description,isOk,status, questionId)
