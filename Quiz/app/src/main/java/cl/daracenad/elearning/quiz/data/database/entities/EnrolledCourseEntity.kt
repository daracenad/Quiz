package cl.daracenad.elearning.quiz.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "enrolled_courses",
    indices = [
        Index(value=["schoolId","studentId","matriculateId","courseId"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("studentId")
        ),
        ForeignKey(
            entity = MatriculateEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("matriculateId")
        ),
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("courseId")
        ),
    ]
)
data class EnrolledCourseEntity(
    @PrimaryKey @ColumnInfo(name = "id") @NotNull var id:String,
    @ColumnInfo(name = "studentId") @NotNull var studentId:String,
    @ColumnInfo(name = "schoolId") @NotNull var schoolId:String,
    @ColumnInfo(name = "courseId") @NotNull var courseId:String,
    @ColumnInfo(name = "matriculateId") @NotNull var matriculateId:String,
    @ColumnInfo(name = "status") @NotNull var status:String
)
{
    companion object {
        const val TABLE_NAME = "enrolled_courses"
    }
}
