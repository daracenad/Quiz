package cl.daracenad.elearning.exercises.data.database.dao

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.daracenad.elearning.exercises.data.database.entities.CourseEntity

@Dao
interface ICourseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: CourseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insertAll(entity: List<CourseEntity>)

    @Update
    suspend fun update(vararg entity: CourseEntity)

    @Delete
    suspend fun delete(vararg entity: CourseEntity)

    @Query("select * from  ${CourseEntity.TABLE_NAME}")
    fun all(): List<CourseEntity>

    @Query("select exists(select * from  ${CourseEntity.TABLE_NAME} where status = 'PD')")
    fun isPendingInstallation(): Boolean

    @Query("select * from  ${CourseEntity.TABLE_NAME} where status = 'PD'")
    fun pendingInstallation(): List<CourseEntity>
}