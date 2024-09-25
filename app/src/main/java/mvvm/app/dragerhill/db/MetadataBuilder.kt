package mvvm.app.dragerhill.db

import android.content.Context
import androidx.room.Room

object MetadataBuilder {

    private var INSTANCE: MetadataAppDatabase? = null

    fun getMetadataInstance(context: Context): MetadataAppDatabase {
        return INSTANCE ?: synchronized(MetadataAppDatabase::class) {
            val instance = buildRoomDB(context)
            INSTANCE = instance
            instance
        }
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            MetadataAppDatabase::class.java,
            "metadataDB"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
}
