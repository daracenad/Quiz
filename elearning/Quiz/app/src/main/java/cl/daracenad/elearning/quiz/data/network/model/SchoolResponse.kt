package cl.daracenad.elearning.quiz.data.network.model

import cl.daracenad.elearning.quiz.data.network.model.serialize.SchoolSerialize
import cl.daracenad.elearning.quiz.data.network.model.serialize.StudentSerialize
import cl.daracenad.elearning.quiz.data.network.model.serialize.UserSerialize
import com.google.gson.annotations.SerializedName

data class SchoolResponse(
    @SerializedName("success") val success:Int,
    @SerializedName("messages") val messages: List<String?>?,
    @SerializedName("school") val school: SchoolSerialize?
)
