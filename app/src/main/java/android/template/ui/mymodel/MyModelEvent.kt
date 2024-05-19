package android.template.ui.mymodel

import android.template.domain.model.MyModel

sealed class MyModelEvent {
    data class UpsertMyModel(val myModel: MyModel): MyModelEvent()
}