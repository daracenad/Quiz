package cl.daracenad.elearning.quiz.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.daracenad.elearning.quiz.data.database.dao.ICourseDAO
import cl.daracenad.elearning.quiz.data.database.dao.IEnrolledCourseDAO
import cl.daracenad.elearning.quiz.data.database.dao.IMatriculateDAO
import cl.daracenad.elearning.quiz.data.database.dao.ISchoolDAO
import cl.daracenad.elearning.quiz.data.database.dao.IStudentDAO
import cl.daracenad.elearning.quiz.data.database.entities.CourseEntity
import cl.daracenad.elearning.quiz.data.database.entities.EnrolledCourseEntity
import cl.daracenad.elearning.quiz.data.database.entities.MatriculateEntity
import cl.daracenad.elearning.quiz.data.database.entities.SchoolEntity
import cl.daracenad.elearning.quiz.data.database.entities.StudentEntity
import cl.daracenad.elearning.quiz.domain.model.school.Course
import cl.daracenad.elearning.quiz.domain.model.school.EnrolledCourse
import cl.daracenad.elearning.quiz.domain.model.school.Matriculate
import cl.daracenad.elearning.quiz.domain.model.school.School
import cl.daracenad.elearning.quiz.domain.model.school.Student
import cl.daracenad.elearning.quiz.domain.model.school.toDomain
import cl.daracenad.elearning.quiz.domain.model.school.toEntity
import cl.daracenad.elearning.quiz.utils.db.DataBaseResult
import cl.daracenad.elearning.quiz.utils.usercase.DTOResult
import javax.inject.Inject

class RepositorySchoolDB @Inject constructor(
    private val schoolDAO: ISchoolDAO,
    private val studentDAO: IStudentDAO,
    private val matriculateDAO: IMatriculateDAO,
    private val courseDAO: ICourseDAO,
    private val enrolledCourseDAO: IEnrolledCourseDAO
) {

    suspend fun schoolInsertFromDB(school: School){
        val schoolEntity = school.toEntity()
        schoolDAO.insert(schoolEntity)
    }

    fun schoolAll(): List<SchoolEntity> {
        return schoolDAO.all()
    }

    suspend fun studentInsert(student: Student){
        val studentEntity = student.toEntity()
        studentDAO.insert(studentEntity)
    }

    suspend fun courseInsert(schoolId:String, course: Course, status:String){
        val entity = course.toEntity(schoolId, status)
        courseDAO.insert(entity)
    }

    fun coursesPendingInstallation(): List<Course> {
        val entities =  courseDAO.pendingInstallation()
        var domain : List<Course> = emptyList()
        entities.forEach{
            domain += it.toDomain()
        }
        return domain

    }

    fun isPendingInstallation(): Boolean {
        return courseDAO.isPendingInstallation()
    }

    suspend fun installedCourse(id:String){
        courseDAO.installed(id)
    }

    fun courseAll(): List<CourseEntity> {
        return courseDAO.all()
    }

    suspend fun matriculateInsert(schoolId:String, studentId:String,matriculate:Matriculate,status:String){
        val entity = matriculate.toEntity(schoolId, studentId,status)
        matriculateDAO.insert(entity)
    }

    suspend fun enrolledInsert(enrolledCourse: EnrolledCourse,studentId:String, schoolId:String, courseId:String, matriculateId:String,status:String){
        val entity = enrolledCourse.toEntity(studentId, schoolId, courseId, matriculateId,status)
        enrolledCourseDAO.insert(entity)
    }

    fun enrolledAll(): List<EnrolledCourseEntity> {
        return enrolledCourseDAO.all()
    }

    fun studentFindForId(id:String): StudentEntity {
        return studentDAO.findForId(id)
    }
    fun studentAll(): LiveData<List<StudentEntity>> {
        return studentDAO.all()
    }

    fun matriculateFindForId(id:String): LiveData<MatriculateEntity> {
        return matriculateDAO.findId(id)
    }

    fun matriculateExists(): Boolean {
        return matriculateDAO.exists()
    }

    fun matriculateAll(): List<MatriculateEntity> {
        return matriculateDAO.all()
    }



}