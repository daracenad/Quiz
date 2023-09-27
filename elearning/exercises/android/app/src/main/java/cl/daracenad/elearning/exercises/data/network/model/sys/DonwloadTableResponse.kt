package cl.daracenad.elearning.exercises.data.network.model.sys


import cl.daracenad.elearning.exercises.data.network.model.serialize.DownloadTableSerialize
import com.google.gson.annotations.SerializedName

data class DonwloadTableResponse(
    @SerializedName("success") val success:Int,
    @SerializedName("messages") val messages: List<String>,
    @SerializedName("downloadTables") val downloadTables:List<DownloadTableSerialize>
)
