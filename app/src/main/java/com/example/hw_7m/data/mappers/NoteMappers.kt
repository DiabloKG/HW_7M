package com.example.hw_7m.data.mappers

import com.example.hw_7m.data.model.NoteEntity
import com.example.hw_7m.domain.model.Note

fun Note.toEntity() = NoteEntity(id, title, description)

fun NoteEntity.toNote() = Note(id, title, description)