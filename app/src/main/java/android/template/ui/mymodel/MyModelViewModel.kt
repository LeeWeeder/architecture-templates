/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.template.ui.mymodel

import android.template.domain.usecases.MyModelUseCases
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyModelViewModel @Inject constructor(
    private val myModelUseCases: MyModelUseCases
) : ViewModel() {

    private val _myModelUiState = mutableStateOf(MyModelUiState())
    val myModelUiState: State<MyModelUiState> = _myModelUiState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var getMyModelsJob: Job? = null

    init {
        getMyModels()
    }


    fun onEvent(event: MyModelEvent) {
        when (event) {
            is MyModelEvent.UpsertMyModel -> {
                viewModelScope.launch {
                    try {
                        myModelUseCases.upsertMyModel(
                            event.myModel
                        )
                        _eventFlow.emit(UiEvent.SaveMyModel)
                    } catch (e: Exception) {
                        _eventFlow.emit(UiEvent.ShowMessage("Couldn't save myModel"))
                    }
                }
            }
        }
    }

    private fun getMyModels() {
        getMyModelsJob?.cancel()
        getMyModelsJob = myModelUseCases.getMyModels()
            .onEach { myModels ->
                _myModelUiState.value = myModelUiState.value.copy(
                    myModels = myModels
                )
            }
            .launchIn(viewModelScope)
    }

    sealed class UiEvent {
        data object SaveMyModel : UiEvent()
        data class ShowMessage(val string: String): UiEvent()
    }
}