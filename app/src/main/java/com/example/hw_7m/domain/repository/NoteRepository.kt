package com.example.hw_7m.domain.repository

import com.example.hw_7m.domain.model.Note
import com.example.hw_7m.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAllNotes(): Flow<Resource<List<Note>>>

    fun createNote(note: Note) : Flow<Resource<Unit>>

    fun updateNote(note: Note) : Flow<Resource<Unit>>

    fun deleteNote(note: Note) : Flow<Resource<Unit>>
}