package android.template.data.datasource

import android.template.domain.model.MyModel
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MyModel::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract val myModelDao: MyModelDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}