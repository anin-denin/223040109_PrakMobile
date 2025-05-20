package com.anin.ppm06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anin.ppm06.models.Note
import com.anin.ppm06.ui.NoteItem
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteApp()
        }
    }
}

@Composable
fun NoteApp(noteViewModel: NoteViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var editingIndex by remember { mutableIntStateOf(-1) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val note = Note(title, description)
                if (editingIndex == -1) {
                    noteViewModel.addNote(note)
                } else {
                    noteViewModel.updateNote(editingIndex, note)
                    editingIndex = -1
                }
                title = ""
                description = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (editingIndex == -1) "Save Note" else "Update Note")
        }

        Spacer(modifier = Modifier.height(16.dp))

        noteViewModel.notes.forEachIndexed { index, note ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = "Title: ${note.title}", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Description: ${note.description}", style = MaterialTheme.typography.bodyMedium)

                    Row(modifier = Modifier.padding(top = 8.dp)) {
                        Button(
                            onClick = {
                                title = note.title
                                description = note.description
                                editingIndex = index
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Edit")
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = {
                                noteViewModel.deleteNote(index)
                                if (editingIndex == index) {
                                    editingIndex = -1
                                    title = ""
                                    description = ""
                                }
                            },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}
