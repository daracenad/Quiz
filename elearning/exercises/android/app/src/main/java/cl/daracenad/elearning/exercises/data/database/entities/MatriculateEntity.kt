package cl.daracenad.elearning.exercises.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(
    tableName = "matriculates",
    indices = [
        Index(value = ["studentId"], unique = false),
        Index(value = ["schoolId"], unique = false)
    ],
    foreignKeys = [
        ForeignKey(
            entity = SchoolEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("schoolId")
        ),
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("studentId")
        )
    ]
)
class MatriculateEntity(
    @PrimaryKey @ColumnInfo(name = "id") @NotNull var id: String,
    @ColumnInfo(name = "schoolId") var schoolId: String,
    @ColumnInfo(name = "studentId") var studentId: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "enrolled_date") var enrolledDate: Int,
    @ColumnInfo(name = "enrolled_expirate") var enrolledExpirate: Int,
    @ColumnInfo(name = "status") @NotNull var status: String
) {
    companion object {
        const val TABLE_NAME = "matriculates"
    }
}