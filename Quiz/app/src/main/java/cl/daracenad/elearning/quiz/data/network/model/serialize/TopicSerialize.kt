package cl.daracenad.elearning.quiz.data.network.model.serialize

import com.google.gson.annotations.SerializedName

data class TopicSerialize(
    @SerializedName("topicId") val id:String,
    @SerializedName("schoolId") val schoolId:String,
    @SerializedName("courseId") val courseId:String,
    @SerializedName("topicName") val name:String,
    @SerializedName("topicDescription") val description:String
    )
