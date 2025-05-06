package com.example.ppm05.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ppm05.data.local.Note
import com.example.ppm05.data.local.NoteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val dao: NoteDao) : ViewModel() {
    val notes = dao.getAll().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addNote(text: String) {
        viewModelScope.launch {
            dao.insert(Note(text = text))
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            dao.delete(note)
        }
    }
}
