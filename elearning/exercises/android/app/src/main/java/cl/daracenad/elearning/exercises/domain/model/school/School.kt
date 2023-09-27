package cl.daracenad.elearning.exercises.domain.model.school

import cl.daracenad.elearning.exercises.data.database.entities.SchoolEntity
import cl.daracenad.elearning.exercises.data.network.model.serialize.SchoolSerialize

data class School(
    val id:String,
    val name:String,
    val description:String,
    var student: Student?,
    var courses: List<Course>,
    var token:String?
)

fun SchoolEntity.toDomain() = School(id =id, name =name, description = description, student = null,token = "",courses = emptyList())
fun School.toEntity() = SchoolEntity(id=id,name=name, description=description, status = "AC")
fun SchoolSerialize.toDomain() = School(id =id, name =name, description = description, student=null,token=token, courses = emptyList())
