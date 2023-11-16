package com.runitrut.rutgers_revised_notes_app

import android.app.Application
import com.runitrut.rutgers_revised_notes_app.Repository.NotesRepository
import com.runitrut.rutgers_revised_notes_app.Room.NotesDatabase

class NotesApplication: Application() {
    private val database by lazy { NotesDatabase.getDatabase(this) }
    val repository by lazy { NotesRepository(database.getNotesDao()) }
}