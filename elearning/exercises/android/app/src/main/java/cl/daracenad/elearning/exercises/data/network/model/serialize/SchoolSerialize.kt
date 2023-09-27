package cl.daracenad.elearning.exercises.data.network.model.serialize

import com.google.gson.annotations.SerializedName

data class SchoolSerialize (
    @SerializedName("id") val id:String,
    @SerializedName("name") val name:String ,
    @SerializedName("description") val description : String,
    @SerializedName("token") val token : String?,
    @SerializedName("student") val student : StudentSerialize?,
    @SerializedName("courses") val courses : List<CourseSerialize>?,

)
