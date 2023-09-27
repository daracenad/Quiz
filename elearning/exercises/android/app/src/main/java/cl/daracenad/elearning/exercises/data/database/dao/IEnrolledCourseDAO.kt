package cl.daracenad.elearning.exercises.data.database.dao

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.daracenad.elearning.exercises.data.database.entities.EnrolledCourseEntity


@Dao
interface IEnrolledCourseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: EnrolledCourseEntity):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insertAll(entity: List<EnrolledCourseEntity>)

    @Update
    suspend fun update(vararg entity: EnrolledCourseEntity)

    @Delete
    suspend fun delete(vararg entity: EnrolledCourseEntity)

    @Query("select * from ${EnrolledCourseEntity.TABLE_NAME}" )
    fun all(): List<EnrolledCourseEntity>

}