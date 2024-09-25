package mvvm.app.dragerhill.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        MetadataAppDatabase::class.java,
        "metadataDB"
    ).build()

    @Provides
    fun provideChannelDao(metadataAppDatabase: MetadataAppDatabase) =
        metadataAppDatabase.metadataDao()

    @Provides
    fun provideMainRepository(channelDao: MetadataDao) = MetadataRepo(channelDao)


}