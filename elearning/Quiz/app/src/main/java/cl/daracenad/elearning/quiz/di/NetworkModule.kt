package cl.daracenad.elearning.quiz.di

import cl.daracenad.elearning.quiz.utils.http.HeaderInterceptor

import cl.daracenad.elearning.quiz.data.network.IDownloadAPIClient
import cl.daracenad.elearning.quiz.data.network.ISchoolAPI
import cl.daracenad.elearning.quiz.data.network.IUserAPI
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    const val URL_API = "https://buscameaqui.cl/api/"
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getClient())
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIClient(retrofit: Retrofit):IDownloadAPIClient{
        return retrofit.create(IDownloadAPIClient::class.java)
    }
    @Singleton
    @Provides
    fun provideSchoolAPI(retrofit: Retrofit):ISchoolAPI{
        return retrofit.create(ISchoolAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideUserAPI(retrofit: Retrofit):IUserAPI{
        return retrofit.create(IUserAPI::class.java)
    }

    private fun getClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()

        return client
    }
}