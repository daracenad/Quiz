package cl.daracenad.elearning.exercises.data.network.model.test

import cl.daracenad.elearning.exercises.data.network.model.serialize.TopicSerialize
import com.google.gson.annotations.SerializedName

data class TopicResponse(
    @SerializedName("success") val success:Int,
    @SerializedName("messages") val messages: List<String>,
    @SerializedName("version") val version: Int,
    @SerializedName("download") val download: Boolean,
    @SerializedName("topics") val topics:List<TopicSerialize>
)

