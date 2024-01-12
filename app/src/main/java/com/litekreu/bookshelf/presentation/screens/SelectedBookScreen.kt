package com.litekreu.bookshelf.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.litekreu.bookshelf.data.model.BookEntity

@Composable
fun SelectedBookScreen(
    book: BookEntity,
    onClose: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

    }
}