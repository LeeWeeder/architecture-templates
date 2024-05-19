package android.template.ui.mymodel

import android.template.domain.model.MyModel

data class MyModelUiState(
    val myModels: List<MyModel> = emptyList()
)