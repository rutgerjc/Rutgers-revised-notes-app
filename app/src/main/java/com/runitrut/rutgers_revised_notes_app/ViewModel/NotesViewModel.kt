package com.runitrut.rutgers_revised_notes_app.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.runitrut.rutgers_revised_notes_app.Model.Note
import com.runitrut.rutgers_revised_notes_app.Repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(private val repository: NotesRepository): ViewModel() {

    var noteItem: LiveData<List<Note>> = repository.allNotes.asLiveData()

    fun deleteNote ( note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
    fun updateNote ( note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }
    fun addNote ( note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}

class NotesViewModelFactory(private val repository: NotesRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NotesViewModel::class.java))
            return NotesViewModel(repository) as T
        throw IllegalArgumentException("unknown ViewModel")
    }
}