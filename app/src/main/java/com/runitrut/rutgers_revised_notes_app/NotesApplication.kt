package com.runitrut.rutgers_revised_notes_app

import android.app.Application

class NotesApplication: Application() {
    private val database by lazy { NotesDatabase.getDatabase(this) }
    val repository by lazy { NotesRepository(database.getNotesDao()) }
}