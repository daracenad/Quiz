package cl.daracenad.elearning.quiz.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "courses",
    indices = [Index(value=["schoolId"], unique = false)],
    foreignKeys = [ForeignKey(
        entity = SchoolEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("schoolId")
    )
    ]
)
data class CourseEntity(
    @PrimaryKey @ColumnInfo(name = "id") @NotNull var id:String,
    @ColumnInfo(name = "schoolId") @NotNull var schoolId:String,
    @ColumnInfo(name = "name") @NotNull var name:String,
    @ColumnInfo(name = "description") var description:String,
    @ColumnInfo(name = "type") @NotNull var type:String,
    @ColumnInfo(name = "duration") @NotNull var duration:Int,
    @ColumnInfo(name = "status") @NotNull var status:String
){
    companion object {
        const val TABLE_NAME = "courses"
    }
}