package com.example.hw_7m.domain.repository

import com.example.hw_7m.domain.model.Note

interface NoteRepository {

    fun getAllNotes(): List<Note>

    fun createNote(note: Note)

    fun updateNote(note: Note)

    fun deleteNote(note: Note)
}