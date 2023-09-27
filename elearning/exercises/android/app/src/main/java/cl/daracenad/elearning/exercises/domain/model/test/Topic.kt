package cl.daracenad.elearning.exercises.domain.model.test

import cl.daracenad.elearning.exercises.data.database.entities.TopicEntity
import cl.daracenad.elearning.exercises.data.network.model.serialize.TopicSerialize

data class Topic (
    val id:String,
    val schoolId:String,
    val courseId:String,
    val name:String,
    val description:String,
    val status:String,
    )
//permite crear un objeto model a partir de un serialize
fun TopicSerialize.toDomain() = Topic(id = id, schoolId=schoolId, courseId = courseId, name = name, description = description,status = "AC" )
fun TopicEntity.toDomain() = Topic(id = id, schoolId=schoolId, courseId = courseId, name = name, description = description,status = "AC" )
fun Topic.toEntity() = TopicEntity(id = id, schoolId=schoolId, courseId = courseId, name = name, description = description,status = "AC" )
