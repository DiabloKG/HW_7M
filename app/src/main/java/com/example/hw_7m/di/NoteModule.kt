package com.example.hw_7m.di

import android.content.Context
import androidx.room.Room
import com.example.hw_7m.data.local.NoteDao
import com.example.hw_7m.data.local.NoteDatabase
import com.example.hw_7m.data.repository.NoteRepositoryImpl
import com.example.hw_7m.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Singleton
    @Provides
    fun provideNoteDataBase(
        @ApplicationContext context: Context
    ) : NoteDatabase = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "note_db")
        .allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository = NoteRepositoryImpl(noteDao)
}