package com.runitrut.rutgers_revised_notes_app.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.runitrut.rutgers_revised_notes_app.Model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    // @Insert method is called,
    // adding a new entity to the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    // @Delete method is called,
    // deleting the entity
    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): Flow<List<Note>>

    // @Update method is called,
    // updating the current entity
    @Update
    suspend fun update(note: Note)
}