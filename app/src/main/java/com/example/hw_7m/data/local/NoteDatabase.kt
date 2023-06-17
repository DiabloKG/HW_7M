package com.example.hw_7m.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hw_7m.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun noteDao(): NoteDao
}