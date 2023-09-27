package cl.daracenad.elearning.quiz.data.network.model.serialize

import cl.daracenad.elearning.quiz.data.database.entities.DownloadTableEntity

data class DownloadTableSerialize(val id:Int, val schoolId:String, val tableName:String, val version:String, val status:String)

