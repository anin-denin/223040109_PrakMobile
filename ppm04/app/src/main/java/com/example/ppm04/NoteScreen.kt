package com.example.ppm04


import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ppm04.models.Note
import kotlinx.coroutines.launch

class NoteViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = NoteDatabase.getDatabase(app).noteDao()
    val notes: LiveData<List<Note>> = dao.getAllNotes()

    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            dao.insertNote(Note(title = title, content = content))
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            dao.deleteNote(note)
        }
    }
}

@Composable
fun NoteScreen(viewModel: NoteViewModel = viewModel(factory = object : ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(Application()) as T
    }
})) {
    val notes by viewModel.notes.observeAsState(emptyList())
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        OutlinedTextField(value = content, onValueChange = { content = it }, label = { Text("Content") })
        Button(onClick = {
            if (title.isNotBlank() && content.isNotBlank()) {
                viewModel.addNote(title, content)
                title = ""
                content = ""
            }
        }, Modifier.padding(top = 8.dp)) {
            Text("Save Note")
        }

        LazyColumn(Modifier.padding(top = 16.dp)) {
            items(notes) { note ->
                Card(Modifier.padding(vertical = 4.dp).fillMaxWidth()) {
                    Column(Modifier.padding(8.dp)) {
                        Text(note.title, style = MaterialTheme.typography.h6)
                        Text(note.content, style = MaterialTheme.typography.body1)
                        Button(onClick = { viewModel.deleteNote(note) }, Modifier.padding(top = 4.dp)) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}
