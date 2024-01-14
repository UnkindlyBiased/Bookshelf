package com.litekreu.bookshelf.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.litekreu.bookshelf.R
import com.litekreu.bookshelf.domain.MainViewModel
import com.litekreu.bookshelf.domain.event.BookEvent
import com.litekreu.bookshelf.presentation.elements.BookItem
import com.litekreu.bookshelf.presentation.elements.ShelfTopBar
import com.litekreu.bookshelf.ui.theme.googleFamily

@Composable
fun BooksScreen(
    viewModel: MainViewModel,
    onEvent: (BookEvent) -> Unit,
    onOpen: () -> Unit
) {
    val booksList by viewModel.booksList.collectAsStateWithLifecycle(initialValue = emptyList())
    
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(BookEvent.AddBook("title", "desc", 1)) }) {
                Row(modifier = Modifier.padding(12.dp)) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(R.string.add_book),
                        fontFamily = googleFamily,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp)
                    )
                }
            }
        },
        topBar = { ShelfTopBar() }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = it.calculateTopPadding()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(booksList) { book ->
                BookItem(
                    book = book,
                    onDelete = { onEvent(BookEvent.DeleteBook(book)) },
                    onOpen = {
                        onEvent(BookEvent.OpenBook(book))
                        onOpen()
                    }
                )
            }
        }
    }
}