package cl.daracenad.elearning.quiz.domain.model.school

import cl.daracenad.elearning.quiz.data.database.entities.EnrolledCourseEntity
import cl.daracenad.elearning.quiz.data.network.model.serialize.EnrolledCourseSerialize

class EnrolledCourse(
    var id:String,
    val courses: List<Course>?
)
fun EnrolledCourseSerialize.toDomain(courses: List<Course>?) =
    EnrolledCourse(id = id, courses = courses)
fun EnrolledCourseEntity.toDomain() =
    EnrolledCourse(id, null)
fun EnrolledCourse.toEntity(studentId:String, schoolId:String, courseId:String, matriculateId:String, status:String) =
    EnrolledCourseEntity(id = id,studentId = studentId, schoolId = schoolId, courseId = courseId, matriculateId = matriculateId, status = status)
