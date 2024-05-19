package android.template.data.repository

import android.template.data.datasource.MyModelDao
import android.template.domain.model.MyModel
import android.template.domain.repository.MyModelRepository
import kotlinx.coroutines.flow.Flow

class MyModelRepositoryImpl(
    private val dao: MyModelDao
): MyModelRepository {
    override fun getMyModels(): Flow<List<MyModel>> {
        return dao.getMyModels()
    }

    override suspend fun getMyModelById(id: Int): MyModel {
        return dao.getMyModelById(id)
    }

    override suspend fun upsertMyModel(myModel: MyModel) {
        dao.upsertMyModel(myModel)
    }

    override suspend fun deleteMyModel(myModel: MyModel) {
        dao.deleteMyModel(myModel)
    }

}