package cl.daracenad.elearning.quiz.di

import android.content.Context
import androidx.room.Room
import cl.daracenad.elearning.quiz.data.database.ExerciseDB

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    const val DATABASE_NAME = "exercise_db"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context:Context)
    = Room.databaseBuilder(context,
        ExerciseDB::class.java,DATABASE_NAME )
        .fallbackToDestructiveMigration()
        .build()


    @Singleton
    @Provides
    fun provideAnswerDAO(db:ExerciseDB) = db.answerDAO()

    @Singleton
    @Provides
    fun provideMatriculateDAO(db:ExerciseDB) = db.matriculateDAO()


    @Singleton
    @Provides
    fun provideEnrolledCourseDAO(db:ExerciseDB) = db.enrolledCourseDAO()


    @Singleton
    @Provides
    fun provideCourseDAO(db:ExerciseDB) = db.courseDAO()

    @Singleton
    @Provides
    fun provideTestTemplateDAO(db:ExerciseDB) = db.testTemplateDAO()

    @Singleton
    @Provides
    fun provideTopicDAO(db:ExerciseDB) = db.topicDAO()

    @Singleton
    @Provides
    fun provideQuestionDAO(db:ExerciseDB) = db.questionDAO()

    @Singleton
    @Provides
    fun provideDownloadTableDAO(db:ExerciseDB) = db.downloadTableDAO()

    @Singleton
    @Provides
    fun provideStudentDAO(db:ExerciseDB) = db.studentDAO()

    @Singleton
    @Provides
    fun provideSchoolDAO(db:ExerciseDB) = db.schoolDAO()

    @Singleton
    @Provides
    fun provideParameterDAO(db:ExerciseDB) = db.parameterDAO()

}