package com.example.hw_7m.domain.usecase

import com.example.hw_7m.domain.model.Note
import com.example.hw_7m.domain.repository.NoteRepository

class UpdateNoteUseCase(
    private val noteRepository: NoteRepository
) {
    fun createNote(note: Note) = noteRepository.updateNote(note)
}