package cl.daracenad.elearning.quiz.data.network.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("success") val success:Int,
    @SerializedName("messages") val messages: List<String>,
    @SerializedName("email") val email:String,
    @SerializedName("name") val name:String,
    @SerializedName("schoolId") val schoolId:String,
    @SerializedName("token") val access_token:String
)
