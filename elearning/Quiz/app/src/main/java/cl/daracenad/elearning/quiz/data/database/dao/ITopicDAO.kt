package cl.daracenad.elearning.quiz.data.database.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.daracenad.elearning.quiz.data.database.entities.TopicEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ITopicDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: TopicEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insertAll(entity: List<TopicEntity>)

    @Update
    suspend fun update(vararg entity: TopicEntity)

    @Delete
    suspend fun delete(vararg entity: TopicEntity)

    @Query("SELECT * FROM " + TopicEntity.TABLE_NAME)
    fun all(): List<TopicEntity>
}