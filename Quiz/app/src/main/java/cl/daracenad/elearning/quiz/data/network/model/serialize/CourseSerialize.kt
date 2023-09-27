package cl.daracenad.elearning.quiz.data.network.model.serialize

import com.google.gson.annotations.SerializedName

data class CourseSerialize(
    @SerializedName("id") val id:String,
    @SerializedName("name") val name :String,
    @SerializedName("description") val description : String,
    @SerializedName("type") val type :String,
    @SerializedName("duration") val duration : Int
    )
