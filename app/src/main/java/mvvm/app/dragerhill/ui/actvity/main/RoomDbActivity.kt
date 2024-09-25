package mvvm.app.dragerhill.ui.actvity.main

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import mvvm.app.dragerhill.R
import mvvm.app.dragerhill.databinding.ActivityRoomDbBinding
import mvvm.app.dragerhill.db.MetaDataViewModel
import mvvm.app.dragerhill.db.MetadataBuilder
import mvvm.app.dragerhill.db.MetadataRepo
import mvvm.app.dragerhill.db.MetadataViewModelFactory
import mvvm.app.dragerhill.model.MetaData

class RoomDbActivity : AppCompatActivity() {
    lateinit var binding: ActivityRoomDbBinding
    private lateinit var metaDataViewModel: MetaDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomDbBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViewModel()

        // Observe the metadata LiveData directly
        metaDataViewModel.allMetadataLiveDataClass.observe(this) { metadataList ->
            loadImage(metadataList) // Call loadImage with the list
        }

        binding.btnClick.setOnClickListener {
            metaDataViewModel.metadataSave() // Save the metadata
        }

        binding.btnDelete.setOnClickListener {
            metaDataViewModel.deleteAllMetadata()
        }
    }

    private fun initializeViewModel() {
        val dao = MetadataBuilder.getMetadataInstance(this).metadataDao()
        val repo = MetadataRepo(dao)
        val metadataViewModelFactory = MetadataViewModelFactory(repo, application)
        metaDataViewModel =
            ViewModelProvider(this, metadataViewModelFactory).get(MetaDataViewModel::class.java)
    }

    private fun loadImage(metadataList: List<MetaData>) {
        if (metadataList.isNotEmpty()) {
            val lastMetaData = metadataList.last()
            val imageResId = lastMetaData.image
            val timestamp = lastMetaData.time
            val id = lastMetaData.id

            Log.d(TAG, "Image resource ID from metadata: $imageResId")
            Log.d(TAG, "ID resource ID from metadata: $id")

            try {
                binding.imageView.setImageResource(imageResId)
                binding.timestampTextView.text = timestamp
            } catch (e: Exception) {
                Log.e(TAG, "Set Image Resource error: ${e.message}")
            }
        } else {
            binding.imageView.setImageResource(R.drawable.ic_launcher_foreground)
            binding.timestampTextView.text = ""
            Log.e(TAG, "Metadata list is empty. Cannot load image.")
        }
    }
}
