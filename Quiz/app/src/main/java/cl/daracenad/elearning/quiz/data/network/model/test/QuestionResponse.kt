package cl.daracenad.elearning.quiz.data.network.model.test

import cl.daracenad.elearning.quiz.data.network.model.serialize.QuestionSerialize
import com.google.gson.annotations.SerializedName

data class QuestionResponse(
    @SerializedName("success") val success:Int,
    @SerializedName("messages") val messages: List<String>,
    @SerializedName("version") val version: Int,
    @SerializedName("download") val download: Boolean,
    @SerializedName("questions") val questions:List<QuestionSerialize>
)

