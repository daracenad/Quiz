package cl.daracenad.elearning.exercises.data.network.model.serialize

import com.google.gson.annotations.SerializedName

data class TestTemplateSerialize(
    @SerializedName("schoolId") val schoolId: String,
    @SerializedName("courseId") val courseId: String,
    @SerializedName("topicId") val topicId: String,
    @SerializedName("testTemplateId") val id: String,
    @SerializedName("testTemplateName") val name: String,
    @SerializedName("testTemplateDescription") val description: String,
    @SerializedName("testTemplateType") val type: String
)
