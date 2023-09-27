package cl.daracenad.elearning.exercises.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "schools")
data class SchoolEntity(
    @PrimaryKey @ColumnInfo(name = "id") @NotNull var id:String,
    @ColumnInfo(name = "name") @NotNull var name:String,
    @ColumnInfo(name = "description") var description:String,
    @ColumnInfo(name = "status") @NotNull var status:String
){
    companion object {
        const val TABLE_NAME = "schools"
    }
}