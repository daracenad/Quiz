package cl.daracenad.elearning.quiz.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "topics",
    foreignKeys = [ForeignKey(
        entity = CourseEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("courseId")
    )
    ]
)
data class TopicEntity (
    @PrimaryKey @ColumnInfo(name = "id") @NotNull val id:String,
    @ColumnInfo(name = "schoolId") val schoolId:String,
    @ColumnInfo(name = "courseId") @NotNull val courseId:String,
    @ColumnInfo(name = "name") @NotNull val name:String,
    @ColumnInfo(name = "description") @NotNull val description:String,
    @ColumnInfo(name = "status") @NotNull val status:String,

){
    companion object {
        const val TABLE_NAME = "topics"
    }
}
