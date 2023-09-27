package cl.daracenad.elearning.exercises.domain.model.sys

import cl.daracenad.elearning.exercises.data.database.entities.DownloadTableEntity
import cl.daracenad.elearning.exercises.data.network.model.serialize.DownloadTableSerialize


data class DonwloadTable(
    val id:Int,
    val schoolId:String,
    val tableName:String,
    val version:String,
    val status:String

)
//fun DownloadTableEntity.toDomain() = DonwloadTable(id, schoolId,tableName,version,status)
//  fun DownloadTableSerialize.toDomain() = DonwloadTable(id, schoolId,tableName,version,status)