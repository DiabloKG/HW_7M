package com.example.hw_7m.presentation.fragment.create_edit_note

import com.example.hw_7m.domain.model.Note
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_7m.domain.usecase.CreateNoteUseCase
import com.example.hw_7m.domain.usecase.DeleteNoteUseCase
import com.example.hw_7m.domain.usecase.GetAllNotesUseCase
import com.example.hw_7m.domain.usecase.UpdateNoteUseCase
import com.example.hw_7m.domain.utils.Resource
import com.example.hw_7m.presentation.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateEditNoteViewModel @Inject constructor(
    private val createNotesUseCase: CreateNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase

) : ViewModel() {

    private val _createNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createNoteState: StateFlow<UIState<Unit>> = _createNoteState

    private val _updateNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val updateNoteState: MutableStateFlow<UIState<Unit>> = _updateNoteState

    fun createNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO){
            createNotesUseCase.createNote(note).collect{ res ->
                when (res) {
                    is Resource.Error -> {
                        _createNoteState.value = UIState.Error(res.message!!)
                    }
                    is Resource.Loading -> {
                        _createNoteState.value = UIState.Loading()
                    }

                    is Resource.Success -> {
                        if (res.data != null) {
                            _createNoteState.value = UIState.Success(res.data)
                        }
                    }
                }
            }
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNoteUseCase.updateNote(note).collect { res ->
                when (res) {
                    is Resource.Error -> {
                        _updateNoteState.value = UIState.Error(res.message!!)
                    }

                    is Resource.Loading -> {
                        _updateNoteState.value = UIState.Loading()
                    }

                    is Resource.Success -> {
                        _updateNoteState.value = UIState.Success(res.data!!)
                    }
                }
            }
        }
    }
}
