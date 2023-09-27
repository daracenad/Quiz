package cl.daracenad.elearning.exercises.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.daracenad.elearning.exercises.data.database.dao.IAnswerDAO
import cl.daracenad.elearning.exercises.data.database.dao.ICourseDAO
import cl.daracenad.elearning.exercises.data.database.dao.IDownloadTableDAO
import cl.daracenad.elearning.exercises.data.database.dao.IEnrolledCourseDAO
import cl.daracenad.elearning.exercises.data.database.dao.IMatriculateDAO
import cl.daracenad.elearning.exercises.data.database.dao.IParameterDAO
import cl.daracenad.elearning.exercises.data.database.dao.IQuestionDAO
import cl.daracenad.elearning.exercises.data.database.dao.ISchoolDAO
import cl.daracenad.elearning.exercises.data.database.dao.IStudentDAO
import cl.daracenad.elearning.exercises.data.database.dao.ITestTemplateDAO
import cl.daracenad.elearning.exercises.data.database.dao.ITopicDAO
import cl.daracenad.elearning.exercises.data.database.entities.AnswerEntity
import cl.daracenad.elearning.exercises.data.database.entities.CourseEntity
import cl.daracenad.elearning.exercises.data.database.entities.DownloadTableEntity
import cl.daracenad.elearning.exercises.data.database.entities.EnrolledCourseEntity
import cl.daracenad.elearning.exercises.data.database.entities.MatriculateEntity
import cl.daracenad.elearning.exercises.data.database.entities.AppParameterEntity
import cl.daracenad.elearning.exercises.data.database.entities.QuestionEntity
import cl.daracenad.elearning.exercises.data.database.entities.SchoolEntity
import cl.daracenad.elearning.exercises.data.database.entities.StudentEntity
import cl.daracenad.elearning.exercises.data.database.entities.TestTemplateEntity
import cl.daracenad.elearning.exercises.data.database.entities.TopicEntity

@Database(entities = [
    AnswerEntity::class,
    CourseEntity::class,
    DownloadTableEntity::class,
    EnrolledCourseEntity::class,
    MatriculateEntity::class,
    AppParameterEntity::class,
    QuestionEntity::class,
    SchoolEntity::class,
    StudentEntity::class,
    TestTemplateEntity::class,
    TopicEntity::class
], version = 8)
abstract class ExerciseDB:RoomDatabase() {
    abstract fun answerDAO(): IAnswerDAO
    abstract fun courseDAO(): ICourseDAO
    abstract fun downloadTableDAO(): IDownloadTableDAO
    abstract fun enrolledCourseDAO(): IEnrolledCourseDAO
    abstract fun matriculateDAO(): IMatriculateDAO
    abstract fun parameterDAO(): IParameterDAO
    abstract fun questionDAO(): IQuestionDAO
    abstract fun schoolDAO(): ISchoolDAO
    abstract fun studentDAO(): IStudentDAO
    abstract fun testTemplateDAO(): ITestTemplateDAO
    abstract fun topicDAO(): ITopicDAO
}