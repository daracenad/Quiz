package cl.daracenad.elearning.quiz.data.network

import com.google.gson.annotations.SerializedName

class ReturnResponse(@SerializedName("success") val success:Int,
                     @SerializedName("messages") val messages: List<String>
)