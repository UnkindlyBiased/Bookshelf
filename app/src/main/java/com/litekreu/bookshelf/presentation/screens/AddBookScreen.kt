package com.litekreu.bookshelf.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.litekreu.bookshelf.R
import com.litekreu.bookshelf.domain.event.BookEvent
import com.litekreu.bookshelf.domain.state.AddBookState
import com.litekreu.bookshelf.presentation.elements.ScreenTitleRow
import kotlinx.coroutines.launch

@Composable
fun AddBookScreen(
    state: AddBookState,
    onBook: (BookEvent) -> Unit,
    onBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    onBook(BookEvent.AddBook(
                        title = state.bookName.value,
                        description = state.bookDescription.value,
                        releaseYear = state.bookReleaseYear.value.toInt(),
                        imageLink = state.bookImageUrl.value
                    ))
                }
                scope.launch {
                    onBack()
                }
                scope.launch {
                    state.bookName.value = ""
                    state.bookDescription.value = ""
                    state.bookReleaseYear.value = ""
                    state.bookImageUrl.value = ""
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                ScreenTitleRow(
                    title = stringResource(R.string.add_book),
                    onBack = { onBack() }
                )
            }
            item {
                OutlinedTextField(
                    value = state.bookName.value,
                    onValueChange = { state.bookName.value = it },
                    label = { Text(text = stringResource(R.string.book_name)) },
                    singleLine = true
                )
            }
            item {
                OutlinedTextField(
                    value = state.bookDescription.value,
                    onValueChange = { state.bookDescription.value = it },
                    label = { Text(text = stringResource(R.string.book_desc)) },
                    modifier = Modifier.height(200.dp)
                )
            }
            item {
                OutlinedTextField(
                    value = state.bookReleaseYear.value,
                    onValueChange = { state.bookReleaseYear.value = it },
                    label = { Text(text = stringResource(R.string.book_release_year)) },
                    singleLine = true
                )
            }
            item {
                OutlinedTextField(
                    value = state.bookImageUrl.value,
                    onValueChange = { state.bookImageUrl.value = it },
                    label = { Text(text = stringResource(R.string.book_image_url)) },
                    singleLine = true
                )
            }
        }
    }
}