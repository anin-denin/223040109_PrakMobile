package com.anin.ppm06

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.anin.ppm06.models.Note

class NoteViewModel : ViewModel() {
    private val _notes = mutableStateListOf<Note>()
    val notes: List<Note> = _notes

    fun addNote(note: Note) {
        _notes.add(note)
    }

    fun updateNote(index: Int, newNote: Note) {
        _notes[index] = newNote
    }

    fun deleteNote(index: Int) {
        if (index in _notes.indices) {
            _notes.removeAt(index)
        }
    }
}
