package cl.daracenad.elearning.exercises.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cl.daracenad.elearning.exercises.data.network.model.serialize.DownloadTableSerialize
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "download_tables",
    foreignKeys = [
        ForeignKey(
            entity = SchoolEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("schoolId")
        )
    ]
)
data class DownloadTableEntity(
    @PrimaryKey @ColumnInfo(name = "id") @NotNull val id:Int,
    @ColumnInfo(name = "schoolId") @NotNull val schoolId:String,
    @ColumnInfo(name = "tablename") @NotNull val tableName:String,
    @ColumnInfo(name = "version") @NotNull val version:String,
    @ColumnInfo(name = "status") @NotNull val status:String
    )
{
    companion object {
        const val TABLE_NAME = "download_tables"
    }


}
fun DownloadTableSerialize.toEntity() = DownloadTableEntity(id, schoolId,tableName,version,status)