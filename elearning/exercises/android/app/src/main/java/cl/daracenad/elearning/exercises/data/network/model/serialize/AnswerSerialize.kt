package cl.daracenad.elearning.exercises.data.network.model.serialize

import com.google.gson.annotations.SerializedName

data class AnswerSerialize(
    @SerializedName("schoolId") val schoolId:String,
    @SerializedName("courseId") val courseId:String,
    @SerializedName("topicId") val topicId:String,
    @SerializedName("testTemplateId") val testTemplateId:String,
    @SerializedName("questionId") val questionId:String,
    @SerializedName("answerId") val answerId:String,
    @SerializedName("answerName") val answerName:String,
    @SerializedName("answerDescription") val answerDescription:String,
    @SerializedName("answerIsOk") val answerIsOk:Boolean
)


