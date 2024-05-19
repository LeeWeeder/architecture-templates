package android.template.data.repository

import android.template.domain.model.MyModel
import android.template.domain.repository.MyModelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeMyModelRepository @Inject constructor() : MyModelRepository {
    private val myModels = mutableListOf<MyModel>()

    override fun getMyModels(): Flow<List<MyModel>> {
        return flow { emit(myModels) }
    }

    override suspend fun getMyModelById(id: Int): MyModel {
        return myModels.find { it.id == id }!!
    }

    override suspend fun upsertMyModel(myModel: MyModel) {
        if (myModel.id !in 0..myModels.size) {
            myModels.add(myModel)
        } else {
            myModels[myModel.id] = myModel
        }
    }

    override suspend fun deleteMyModel(myModel: MyModel) {
        myModels.remove(myModel)
    }
}