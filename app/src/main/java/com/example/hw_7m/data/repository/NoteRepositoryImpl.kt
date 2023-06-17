package com.example.hw_7m.data.repository

import com.example.hw_7m.data.local.NoteDao
import com.example.hw_7m.data.mappers.toEntity
import com.example.hw_7m.data.mappers.toNote
import com.example.hw_7m.data.model.NoteEntity
import com.example.hw_7m.domain.model.Note
import com.example.hw_7m.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes().map { it.toNote() }
    }

    override fun createNote(note: Note) {
        noteDao.createNote(note.toEntity())
    }

    override fun updateNote(note: Note) {
        noteDao.updateNote(note.toEntity())
    }

    override fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toEntity())
    }
}