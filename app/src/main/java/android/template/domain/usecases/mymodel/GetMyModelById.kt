package android.template.domain.usecases.mymodel

import android.template.domain.model.MyModel
import android.template.domain.repository.MyModelRepository

class GetMyModelById(
    private val repository: MyModelRepository
) {
    suspend operator fun invoke(id: Int): MyModel {
        return repository.getMyModelById(id)
    }
}