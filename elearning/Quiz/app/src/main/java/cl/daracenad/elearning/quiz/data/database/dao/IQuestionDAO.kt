package cl.daracenad.elearning.quiz.data.database.dao

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.daracenad.elearning.quiz.data.database.entities.QuestionEntity

@Dao
interface IQuestionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: QuestionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insertAll(entity: List<QuestionEntity>)

    @Update
    suspend fun update(vararg entity: QuestionEntity)

    @Delete
    suspend fun delete(vararg entity: QuestionEntity)

    @Query("SELECT * FROM " + QuestionEntity.TABLE_NAME)
    fun all(): List<QuestionEntity>
}