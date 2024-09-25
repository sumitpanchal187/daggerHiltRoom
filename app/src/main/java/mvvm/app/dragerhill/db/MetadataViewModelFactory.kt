package mvvm.app.dragerhill.db

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MetadataViewModelFactory(
    private val metadataRepo: MetadataRepo,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MetaDataViewModel::class.java)) {
            return MetaDataViewModel(application, metadataRepo) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}
