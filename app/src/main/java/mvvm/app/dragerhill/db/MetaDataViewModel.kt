package mvvm.app.dragerhill.db

import android.annotation.SuppressLint
import android.app.Application
import android.os.Environment
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mvvm.app.dragerhill.R
import mvvm.app.dragerhill.model.MetaData
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MetaDataViewModel @Inject constructor(
    val application: Application,
    private val metadataRepo: MetadataRepo
) : ViewModel() {

    private val fileName = "watchmantest.json"
    private val folderName = "ConfigSetting"

    val mContext by lazy { application.applicationContext }

    val allMetadataLiveDataClass: LiveData<List<MetaData>> = metadataRepo.getAllMetadata()

    @SuppressLint("SimpleDateFormat")
    fun metadataSave() {
        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        Log.d("MetaDataViewModel", "Current Date & Time is : $currentDate")
        val type = "Valid"
        val imageResId = R.drawable.ic_launcher_background
        val configurationsFolder = File(Environment.getDataDirectory(), folderName)
        val configFile = File(configurationsFolder, fileName)

        Log.e("configFile", configFile.absolutePath)

        viewModelScope.launch {
            createMetadata(MetaData(0, imageResId, currentDate, type))
        }
    }

    fun deleteMetadata(metadata: MetaData) {
        viewModelScope.launch {
            metadataRepo.deleteMetadata(metadata)
        }
    }

    private fun updateMetadata(metadata: MetaData) {
        viewModelScope.launch {
            metadataRepo.updateMetadata(metadata)
        }
    }

    fun deleteAllMetadata() {
        viewModelScope.launch {
            metadataRepo.deleteAllMetadata()
        }
    }

    fun createMetadata(metadata: MetaData) {
        viewModelScope.launch {
            metadataRepo.createMetadata(metadata)
        }
    }
}
