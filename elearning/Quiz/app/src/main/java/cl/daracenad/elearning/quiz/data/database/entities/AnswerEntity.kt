package cl.daracenad.elearning.quiz.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "answers",
    indices = [Index(value=["questionId"], unique = false)],
    foreignKeys = [ForeignKey(
        entity = QuestionEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("questionId")
    )
    ]
)
data class AnswerEntity(
    @PrimaryKey @ColumnInfo(name = "id") @NotNull var id:String,
    @ColumnInfo(name = "name") @NotNull var name:String,
    @ColumnInfo(name = "description") @NotNull var description:String,
    @ColumnInfo(name = "isOk") @NotNull var isOk:Boolean,
    @ColumnInfo(name = "status") @NotNull var status:String,
    @ColumnInfo(name = "questionId") @NotNull var questionId:String
) {
    companion object {
        const val TABLE_NAME = "answers"
    }
}