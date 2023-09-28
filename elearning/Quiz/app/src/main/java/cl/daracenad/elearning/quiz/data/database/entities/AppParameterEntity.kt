package cl.daracenad.elearning.quiz.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "app_parameters")
data class AppParameterEntity(
    @PrimaryKey @ColumnInfo(name = "id") @NotNull var id:Int,
    @ColumnInfo(name = "schoolId") @NotNull var schoolId:String,
    @ColumnInfo(name = "courseId") @NotNull var courseId:String,
    @ColumnInfo(name = "objectType") @NotNull var objectType:String,
    @ColumnInfo(name = "objectName") @NotNull var objectName:String,
    @ColumnInfo(name = "value") @NotNull var value:String,
    @ColumnInfo(name = "version") @NotNull var version:Int,
    @ColumnInfo(name = "action") @NotNull var action:String,
    @ColumnInfo(name = "status") @NotNull var status:String
){
    companion object {
        const val TABLE_NAME = "app_parameters"
    }
}