package cl.daracenad.elearning.quiz.utils.http


import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    var token : String = "";

    fun Token(token: String ) {
        this.token = token;
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        val finalToken =  "Bearer "+token
        val request = chain.request().newBuilder()
            .addHeader("Accept","application/json")
            .addHeader("Content-Type","application/json; charset=UTF-8")
            //.addHeader("Authorization",finalToken)
            .build()
        val c = chain.proceed(request)

        return c
    }
}
