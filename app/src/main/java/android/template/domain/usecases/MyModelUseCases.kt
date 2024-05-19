package android.template.domain.usecases

import android.template.domain.usecases.mymodel.DeleteMyModel
import android.template.domain.usecases.mymodel.GetMyModelById
import android.template.domain.usecases.mymodel.GetMyModels
import android.template.domain.usecases.mymodel.UpsertMyModel

data class MyModelUseCases(
    val getMyModels: GetMyModels,
    val getMyModelById: GetMyModelById,
    val upsertMyModel: UpsertMyModel,
    val deleteMyModel: DeleteMyModel
)
