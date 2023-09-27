package cl.daracenad.elearning.quiz.data.database.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.daracenad.elearning.quiz.data.database.entities.DownloadTableEntity

@Dao
interface IDownloadTableDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: DownloadTableEntity):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insertAll(entity: List<DownloadTableEntity>)

    @Update
    suspend fun update(vararg entity: DownloadTableEntity)

    @Delete
    suspend fun delete(vararg entity: DownloadTableEntity)

    @Query("SELECT * FROM " + DownloadTableEntity.TABLE_NAME)
    suspend fun all(): DownloadTableEntity
}