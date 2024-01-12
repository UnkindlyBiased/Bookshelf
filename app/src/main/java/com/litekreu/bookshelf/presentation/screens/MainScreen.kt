package com.litekreu.bookshelf.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.litekreu.bookshelf.domain.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        BooksScreen(viewModel = viewModel)
    }
}