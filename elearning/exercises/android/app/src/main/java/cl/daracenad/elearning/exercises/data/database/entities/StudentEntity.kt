package cl.daracenad.elearning.exercises.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "students",
    foreignKeys = [ForeignKey(
        entity = SchoolEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("schoolId")
    )
    ]
)

data class StudentEntity(
    @PrimaryKey @ColumnInfo(name = "id") @NotNull var id:String,
    @ColumnInfo(name = "schoolId") @NotNull var schoolId:String,
    @ColumnInfo("name") val name:String,
    @ColumnInfo("email") val email:String

){
    companion object {
        const val TABLE_NAME = "students"
    }
}
