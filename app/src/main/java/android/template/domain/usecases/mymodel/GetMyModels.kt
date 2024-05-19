package android.template.domain.usecases.mymodel

import android.template.domain.model.MyModel
import android.template.domain.repository.MyModelRepository
import kotlinx.coroutines.flow.Flow

class GetMyModels(
    private val repository: MyModelRepository
) {
    operator fun invoke(): Flow<List<MyModel>> {
        return repository.getMyModels()
    }
}