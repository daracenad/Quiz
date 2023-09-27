package cl.daracenad.elearning.exercises.data.network.model.school

import cl.daracenad.elearning.exercises.data.network.model.serialize.CourseSerialize
import cl.daracenad.elearning.exercises.data.network.model.serialize.SchoolSerialize
import com.google.gson.annotations.SerializedName

data class CourseResponse(
    @SerializedName("success") val success:Int,
    @SerializedName("messages") val messages: List<String>,
    @SerializedName("version") val version: Int,
    @SerializedName("download") val download: Boolean,
    @SerializedName("school") var school: List<SchoolSerialize>
)
