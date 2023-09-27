package cl.daracenad.elearning.quiz.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "test_templates",
    foreignKeys = [ForeignKey(
        entity = TopicEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("topicId")
    )
    ]
)
data class TestTemplateEntity(
    @PrimaryKey @ColumnInfo(name = "id") @NotNull var id:String,
    @ColumnInfo(name = "topicId") @NotNull var topicId:String,
    @ColumnInfo(name = "name")  var name:String,
    @ColumnInfo(name = "description")  var description:String,
    @ColumnInfo(name = "status") @NotNull var status:String,
    @ColumnInfo(name = "type")  var type:String
){
    companion object {
        const val TABLE_NAME = "test_templates"
    }
}
