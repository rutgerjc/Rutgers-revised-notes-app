package com.runitrut.rutgers_revised_notes_app.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.runitrut.rutgers_revised_notes_app.ViewModel.NotesViewModel
import com.runitrut.rutgers_revised_notes_app.ViewModel.NotesViewModelFactory
import com.runitrut.rutgers_revised_notes_app.Model.Note
import com.runitrut.rutgers_revised_notes_app.NotesApplication
import com.runitrut.rutgers_revised_notes_app.R
import java.text.SimpleDateFormat
import java.util.Date

class AddEditNoteActivity : AppCompatActivity() {

    lateinit var noteTitleEdit: EditText
    lateinit var noteDescriptionEdit: EditText
    lateinit var updateButton: Button
    private val notesViewModel: NotesViewModel by viewModels {
        NotesViewModelFactory((application as NotesApplication).repository)
    }
     var noteID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        noteTitleEdit = findViewById(R.id.note_title_EDT)
        noteDescriptionEdit = findViewById(R.id.note_description_EDT)
        updateButton = findViewById(R.id.add_update_BTN)

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")){
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            // noteId was note ID and cause the Updated notes to not apply updated notes
            noteID = intent.getIntExtra("noteId", -1)
            updateButton.text = "Update Note"
            noteTitleEdit.setText(noteTitle)
            noteDescriptionEdit.setText(noteDesc)
        } else {
            updateButton.text = " Save Note"
        }

        updateButton.setOnClickListener {
            val noteTitle = noteTitleEdit.text.toString()
            val noteDescription = noteDescriptionEdit.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("MMM dd, yyyy - hh:mm aaa")
                    val currentDate: String = sdf.format(Date())
                    val updateNote = Note(noteTitle, noteDescription, currentDate)
                    updateNote.id = noteID
                    notesViewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Update...", Toast.LENGTH_LONG).show()
                }
                } else {
                    if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                        val sdf = SimpleDateFormat("MMM dd, yyyy - hh:mm aaa")
                        val currentDate: String = sdf.format(Date())
                        notesViewModel.addNote(Note(noteTitle, noteDescription, currentDate))
                        Toast.makeText(this, " Noted Add", Toast.LENGTH_LONG).show()
                    }
                }
                startActivity(Intent(applicationContext, MainActivity::class.java))
                this.finish()
            }
        }
    }