package com.example.hw_7m.data.repository

import com.example.hw_7m.data.local.NoteDao
import com.example.hw_7m.data.mappers.toEntity
import com.example.hw_7m.data.mappers.toNote
import com.example.hw_7m.data.model.NoteEntity
import com.example.hw_7m.domain.model.Note
import com.example.hw_7m.domain.repository.NoteRepository
import com.example.hw_7m.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun getAllNotes(): Flow<Resource<List<Note>>> = flow {
        emit(Resource.Loading())
        try {
            val data = noteDao.getAllNotes().map{it.toNote()}
            emit(Resource.Success(data))
        }
        catch (e: Exception){
            emit(Resource.Error(e.localizedMessage?: "unknown error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun createNote(note: Note) = flow {
        emit(Resource.Loading())
        try {
            val data = noteDao.createNote(note.toEntity())
            emit(Resource.Success(data))
        }
        catch (e: Exception){
            emit(Resource.Error(e.localizedMessage?: "unknown error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun updateNote(note: Note) = flow {
        emit(Resource.Loading())
        try {
            val data = noteDao.updateNote(note.toEntity())
            emit(Resource.Success(data))
        }
        catch (e: Exception){
            emit(Resource.Error(e.localizedMessage?: "unknown error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun deleteNote(note: Note)  = flow {
        emit(Resource.Loading())
        try {
            val data = noteDao.deleteNote(note.toEntity())
            emit(Resource.Success(data))
        }
        catch (e: Exception){
            emit(Resource.Error(e.localizedMessage?: "unknown error"))
        }
    }.flowOn(Dispatchers.IO)
}