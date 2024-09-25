package mvvm.app.dragerhill.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import mvvm.app.dragerhill.model.MetaData

@Database(entities = [MetaData::class], version = 1, exportSchema = true)
abstract class MetadataAppDatabase : RoomDatabase() {

    abstract fun metadataDao(): MetadataDao

    companion object {
        @Volatile
        private var INSTANCE: MetadataAppDatabase? = null

        fun getDatabase(context: Context): MetadataAppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MetadataAppDatabase::class.java,
                    "metadataDB"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
