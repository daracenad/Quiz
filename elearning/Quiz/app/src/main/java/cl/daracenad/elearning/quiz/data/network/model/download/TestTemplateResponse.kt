package cl.daracenad.elearning.quiz.data.network.model.download

import cl.daracenad.elearning.quiz.data.network.model.serialize.TestTemplateSerialize
import com.google.gson.annotations.SerializedName

data class TestTemplateResponse(
    @SerializedName("success") val success:Int,
    @SerializedName("messages") val messages: List<String>,
    @SerializedName("version") val version: Int,
    @SerializedName("download") val download: Boolean,
    @SerializedName("templates") var templates: List<TestTemplateSerialize>
)
