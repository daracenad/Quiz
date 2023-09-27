package cl.daracenad.elearning.exercises.data.database.dao

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.daracenad.elearning.exercises.data.database.entities.TestTemplateEntity

@Dao
interface ITestTemplateDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: TestTemplateEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insertAll(entity: List<TestTemplateEntity>)

    @Update
    suspend fun update(vararg entity: TestTemplateEntity)

    @Delete
    suspend fun delete(vararg entity: TestTemplateEntity)

    @Query("SELECT * FROM " + TestTemplateEntity.TABLE_NAME)
    fun all(): LiveData<List<TestTemplateEntity>>
}