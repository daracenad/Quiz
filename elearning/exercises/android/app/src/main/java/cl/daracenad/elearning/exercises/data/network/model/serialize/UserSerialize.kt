package cl.daracenad.elearning.exercises.data.network.model.serialize

import com.google.gson.annotations.SerializedName


data class UserSerialize(
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name:String,
    @SerializedName("email") val email:String
)