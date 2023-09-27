package cl.daracenad.elearning.quiz.data.network.model.serialize

import com.google.gson.annotations.SerializedName

data class QuestionSerialize(
    @SerializedName("questionId") val id:String,
    @SerializedName("schoolId") val schoolId:String,
    @SerializedName("courseId") val courseId:String,
    @SerializedName("topicId") val topicId:String,
    @SerializedName("testTemplateId") val testTemplateId:String,
    @SerializedName("questionName") val name:String,
    @SerializedName("questionDescription") val description:String)
