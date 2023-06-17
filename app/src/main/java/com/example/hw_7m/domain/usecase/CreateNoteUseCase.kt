package com.example.hw_7m.domain.usecase

import com.example.hw_7m.domain.model.Note
import com.example.hw_7m.domain.repository.NoteRepository
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    fun createNote(note: Note) = noteRepository.createNote(note)
}