package cl.daracenad.elearning.quiz.domain.model.test

import cl.daracenad.elearning.quiz.data.database.entities.TestTemplateEntity
import cl.daracenad.elearning.quiz.data.network.model.serialize.TestTemplateSerialize


data class TestTemplate (
    val id:String,
    val topicId:String,
    val name:String,
    val description:String,
    val status:String,
    val type:String
)
//permite crear un objeto model a partir de un serialize
fun TestTemplateSerialize.toDomain() = TestTemplate(id = id, topicId=topicId, name = name, description = description,status = "AC" ,type = type)
fun TestTemplateEntity.toDomain() = TestTemplate(id = id, topicId=topicId, name = name, description = description,status = "AC" ,type = type)
fun TestTemplate.toEntity() = TestTemplateEntity(id = id, topicId=topicId, name = name, description = description,status = "AC" ,type = type)
