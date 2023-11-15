package com.runitrut.rutgers_revised_notes_app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase(){
    abstract fun getNotesDao(): NotesDao

    companion object{
        // Single prevents multiple
        // instance of database opening at the same time.
        @Volatile
        private var INSTANCE: NotesDatabase? = null
        fun getDatabase(context: Context): NotesDatabase {
            // if INSTANCE isn't null, then return it,
            // if it is, then create the database
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}