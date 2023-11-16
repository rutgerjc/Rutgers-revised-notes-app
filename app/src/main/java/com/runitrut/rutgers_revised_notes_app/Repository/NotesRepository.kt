package com.runitrut.rutgers_revised_notes_app.Repository

import com.runitrut.rutgers_revised_notes_app.Model.Note
import com.runitrut.rutgers_revised_notes_app.Room.NotesDao
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val notesDao: NotesDao) {

    val allNotes: Flow<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note){
        notesDao.insert(note)
    }

    suspend fun delete(note: Note){
        notesDao.delete(note)
    }

    suspend fun update(note: Note){
        notesDao.update(note)
    }
}