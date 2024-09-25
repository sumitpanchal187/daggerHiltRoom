package mvvm.app.dragerhill.db

import androidx.lifecycle.LiveData
import mvvm.app.dragerhill.model.MetaData
import javax.inject.Inject

class MetadataRepo @Inject constructor(private val metadataDao: MetadataDao) {
//    val allMetaData = metadataDao.getMetadata()

    fun getAllMetadata(): LiveData<List<MetaData>> {
        return metadataDao.getMetadata()
    }
    suspend fun createMetadata(metadata: MetaData) {
        metadataDao.create(metadata)
    }

    suspend fun updateMetadata(metadata: MetaData) {
        metadataDao.update(metadata)
    }

    suspend fun deleteMetadata(metadata: MetaData) {
        metadataDao.delete(metadata)
    }

    suspend fun deleteAllMetadata(){
        metadataDao.deleteAllMetadata()
    }
}
