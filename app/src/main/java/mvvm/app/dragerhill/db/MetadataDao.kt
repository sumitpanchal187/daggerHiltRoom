package mvvm.app.dragerhill.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import mvvm.app.dragerhill.model.MetaData

@Dao
interface MetadataDao {

    @Query("SELECT * FROM metadata_table ORDER BY id ASC")
    fun getMetadata(): LiveData<List<MetaData>>

    @Delete
    suspend fun delete(metadata: MetaData)

    @Insert
    suspend fun create(metadata: MetaData)

    @Update
    suspend fun update(metadata: MetaData)

    @Query("DELETE FROM metadata_table")
    suspend fun deleteAllMetadata()

}
