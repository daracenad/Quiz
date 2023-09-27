package cl.daracenad.elearning.exercises.data.network.model.serialize

import com.google.gson.annotations.SerializedName

data class StudentSerialize (
    @SerializedName("id") val id:String,
    @SerializedName("name") val name:String,
    @SerializedName("email") val email:String,
    @SerializedName("matriculates") var matriculates: List<MatriculateSerialize>?
)