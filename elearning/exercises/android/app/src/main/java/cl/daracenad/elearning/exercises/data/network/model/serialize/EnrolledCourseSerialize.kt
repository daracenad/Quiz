package cl.daracenad.elearning.exercises.data.network.model.serialize

import com.google.gson.annotations.SerializedName

data class EnrolledCourseSerialize(
    @SerializedName("id") val id:String,
    @SerializedName("courses") val courses: List<CourseSerialize>?

)
