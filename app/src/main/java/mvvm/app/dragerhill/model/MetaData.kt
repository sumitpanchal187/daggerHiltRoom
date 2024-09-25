package mvvm.app.dragerhill.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "metadata_table")
data class MetaData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "image") val image: Int,
    @ColumnInfo(name = "time") val time: String,
    @ColumnInfo(name = "type") val type: String,
)
