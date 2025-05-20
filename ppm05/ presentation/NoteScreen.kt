package com.example.ppm05.presentation


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ppm05.data.local.Note

@Composable
fun NoteScreen(viewModel: NoteViewModel) {
    val notes by viewModel.notes.collectAsState()
    var text by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        BasicTextField(value = text, onValueChange = { text = it })
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            viewModel.addNote(text)
            text = ""
        }) {
            Text("Add Note")
        }
        Spacer(modifier = Modifier.height(16.dp))
        notes.forEach {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(it.text)
                Button(onClick = { viewModel.deleteNote(it) }) {
                    Text("Delete")
                }
            }
        }
    }
}
