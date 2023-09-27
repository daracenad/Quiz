package cl.daracenad.elearning.exercises.domain.model.school

import cl.daracenad.elearning.exercises.data.database.entities.MatriculateEntity
import cl.daracenad.elearning.exercises.data.network.model.serialize.EnrolledCourseSerialize
import cl.daracenad.elearning.exercises.data.network.model.serialize.MatriculateSerialize

data class Matriculate(
    val id:String,
    val description:String,
    val enrolledDate:Int,
    val enrolledExpirate:Int,
    var enrolledCourses: List<EnrolledCourse>?

)
//permite crear un objeto model a partir de un otro
fun MatriculateSerialize.toDomain(enrolledCourses:List<EnrolledCourse>?) = Matriculate(id = id, description=description, enrolledDate = enrolledDate, enrolledExpirate=enrolledExpirate, enrolledCourses =enrolledCourses )
fun MatriculateEntity.toDomain(enrolledCourses: List<EnrolledCourse>?) = Matriculate(id = id, description=description, enrolledDate = enrolledDate,  enrolledExpirate=enrolledExpirate, enrolledCourses =enrolledCourses)
fun Matriculate.toEntity(schoolId:String, studentId:String, status:String) =
    MatriculateEntity(id = id, schoolId=schoolId, studentId=studentId, description=description, enrolledDate = enrolledDate, enrolledExpirate=enrolledExpirate,status = status)

