package cl.daracenad.elearning.exercises.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cl.daracenad.elearning.exercises.domain.model.AppParameter
import org.jetbrains.annotations.NotNull
@Entity(tableName = "parameters")
data class AppParameterEntity(
    @PrimaryKey @ColumnInfo(name = "key") @NotNull var key :String,
    @ColumnInfo(name = "valueString") @NotNull var valueString:String,
    @ColumnInfo(name = "valueInt") @NotNull var valueInt:Int,
    @ColumnInfo(name = "valueBA", typeAffinity = ColumnInfo.BLOB)  var valueBA:ByteArray?
    ){
    companion object {
        const val TABLE_NAME = "parameters"
    }
}

