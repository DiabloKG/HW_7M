package com.example.hw_7m.presentation

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw_7m.domain.usecase.DeleteNoteUseCase
import com.example.hw_7m.domain.usecase.GetAllNotesUseCase
import com.example.hw_7m.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListNoteViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getAllNotesState: StateFlow<UIState<List<Note>>> = _getAllNotesState

    fun getAllNotes() {
        viewModelScope.launch{
            getAllNotesUseCase.getAllNotes().collect{ res ->
                when (res) {
                    is Resource.Error -> {
                        _getAllNotesState.value = UIState.Error(res.message!!)
                    }
                    is Resource.Loading -> {
                        _getAllNotesState.value = UIState.Loading()
                    }

                    is Resource.Success -> {
                            _getAllNotesState.value = UIState.Success(res.data)
                    }
                }
            }
        }
    }
}