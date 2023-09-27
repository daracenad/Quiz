package cl.daracenad.elearning.exercises.data.network.model.serialize

import com.google.gson.annotations.SerializedName

data class  MatriculateSerialize (
    @SerializedName("id") val id:String,
    @SerializedName("description") val description:String,
    @SerializedName("enrolledDate") val enrolledDate:Int,
    @SerializedName("enrolledExpirate") val enrolledExpirate:Int,
    @SerializedName("enrolledCourse") val enrolledCourses: List<EnrolledCourseSerialize>?
)