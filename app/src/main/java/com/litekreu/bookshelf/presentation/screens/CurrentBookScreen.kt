package com.litekreu.bookshelf.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.litekreu.bookshelf.data.model.BookEntity
import com.litekreu.bookshelf.domain.MainViewModel

@Composable
fun CurrentBookScreen(book: BookEntity) {
//    val currentBookState by viewModel.currentBook.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun CurrentBookPreview() {
    CurrentBookScreen(book = BookEntity(
        bookName = "Ферма тварин",
        bookReleaseYear = 1945,
        bookDescription = "",
        bookImageUrl = "https://m.media-amazon.com/images/I/71JUJ6pGoIL._AC_UF1000,1000_QL80_.jpg",
        authorRefId = 1
    ))
}