package cl.daracenad.elearning.quiz.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "questions",
    indices = [Index(value=["schoolId","courseId","topicId" ], unique = false)],
    foreignKeys = [ForeignKey(
        entity = TestTemplateEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("testTemplateId")
    )
    ]
)
data class QuestionEntity(
    @PrimaryKey @ColumnInfo(name = "id") @NotNull var id:String,
    @ColumnInfo("schoolId") val schoolId:String,
    @ColumnInfo("courseId") val courseId:String,
    @ColumnInfo("topicId") val topicId:String,
    @ColumnInfo("testTemplateId") val testTemplateId:String,
    @ColumnInfo("name") val name:String,
    @ColumnInfo("description") val description:String,
    @ColumnInfo("status") val status:String
){
    companion object {
        const val TABLE_NAME = "questions"
    }
}
