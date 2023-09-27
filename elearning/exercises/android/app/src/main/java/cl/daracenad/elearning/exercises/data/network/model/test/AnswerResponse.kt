package cl.daracenad.elearning.exercises.data.network.model.test

import cl.daracenad.elearning.exercises.data.database.entities.AnswerEntity
import cl.daracenad.elearning.exercises.data.network.model.serialize.AnswerSerialize
import com.google.gson.annotations.SerializedName


data class AnswerResponse(
    @SerializedName("success") val success:Int,
    @SerializedName("messages") val messages: List<String>,
    @SerializedName("version") val version: Int,
    @SerializedName("download") val download: Boolean,
    @SerializedName("answers") val answers: List<AnswerSerialize>
)


