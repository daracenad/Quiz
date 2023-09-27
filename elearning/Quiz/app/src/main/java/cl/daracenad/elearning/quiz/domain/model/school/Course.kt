package cl.daracenad.elearning.quiz.domain.model.school

import cl.daracenad.elearning.quiz.data.database.entities.CourseEntity
import cl.daracenad.elearning.quiz.data.network.model.serialize.CourseSerialize
import cl.daracenad.elearning.quiz.ui.school.model.CourseSelected

data class Course(
    var id:String,
    val name:String,
    val description:String,
    val type:String,
    val duration:Int,
    val status:String
)

fun CourseSerialize.toDomain(status:String = "PD") =
    Course(id = id, name = name, description = description, type=type, duration=duration, status=status)
fun CourseEntity.toDomain(status: String="PD") =
    Course(id = id,  name = name, description = description, type=type, duration=duration, status=status)
fun Course.toEntity(schoolId:String, status: String="PD") =
    CourseEntity(id = id, schoolId=schoolId, name = name, description = description, type=type, duration=duration, status =status)

