package android.template.domain.usecases.mymodel

import android.template.domain.model.MyModel
import android.template.domain.repository.MyModelRepository

class DeleteMyModel(
    private val repository: MyModelRepository
) {
    suspend operator fun invoke(myModel: MyModel) {
        repository.deleteMyModel(myModel)
    }
}