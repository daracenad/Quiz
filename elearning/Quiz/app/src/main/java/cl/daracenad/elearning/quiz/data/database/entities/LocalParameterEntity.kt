package cl.daracenad.elearning.quiz.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
@Entity(tableName = "local_parameters")
data class LocalParameterEntity(
    @PrimaryKey @ColumnInfo(name = "key") @NotNull var key :String,
    @ColumnInfo(name = "valueString") @NotNull var valueString:String,
    @ColumnInfo(name = "valueInt") @NotNull var valueInt:Int,
    @ColumnInfo(name = "valueBA", typeAffinity = ColumnInfo.BLOB)  var valueBA:ByteArray?
    ){
    companion object {
        const val TABLE_NAME = "local_parameters"
    }
}

