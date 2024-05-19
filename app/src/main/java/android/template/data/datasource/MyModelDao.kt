package android.template.data.datasource

import android.template.domain.model.MyModel
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MyModelDao {
    @Query("SELECT * FROM mymodel")
    fun getMyModels(): Flow<List<MyModel>>

    @Query("SELECT * FROM mymodel WHERE id = :id")
    suspend fun getMyModelById(id: Int): MyModel

    @Upsert
    suspend fun upsertMyModel(myModel: MyModel)

    @Delete
    suspend fun deleteMyModel(myModel: MyModel)
}
