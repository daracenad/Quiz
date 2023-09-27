package cl.daracenad.elearning.quiz.data.database.dao

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.daracenad.elearning.quiz.data.database.entities.AnswerEntity

@Dao
interface IAnswerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: AnswerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insertAll(entity: List<AnswerEntity>)

    @Update
    suspend fun update(vararg entity: AnswerEntity)

    @Delete
    suspend fun delete(vararg entity: AnswerEntity)

    @Query("SELECT * FROM " + AnswerEntity.TABLE_NAME)
    suspend fun all(): List<AnswerEntity>?
}