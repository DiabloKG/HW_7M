package com.example.hw_7m.domain.usecase

import com.example.hw_7m.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    fun getAllNotes() = noteRepository.getAllNotes()
}