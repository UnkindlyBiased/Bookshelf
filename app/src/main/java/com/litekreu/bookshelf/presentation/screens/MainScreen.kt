package com.litekreu.bookshelf.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.litekreu.bookshelf.domain.MainViewModel

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel
) {
    NavHost(navController = navController, startDestination = "books") {
        composable(route = "books") {
            Box(modifier = Modifier.fillMaxSize()) {
                BooksScreen(
                    viewModel = viewModel,
                    onEvent = viewModel::onBookEvent,
                    onOpen = { navController.navigate("currentBook") }
                )
            }
        }
        composable(route = "currentBook") {
            val currentBookState by viewModel.currentBook.collectAsStateWithLifecycle()

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = currentBookState.currentBook?.bookName ?: "Null")
            }
        }
    }
}

/*

* */