package com.litekreu.bookshelf.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.litekreu.bookshelf.domain.MainViewModel
import com.litekreu.bookshelf.util.NavGraph

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = hiltViewModel()
) {
    NavHost(navController = navController, startDestination = NavGraph.Books) {
        composable(route = NavGraph.Books) {
            val booksState by viewModel.booksState.collectAsStateWithLifecycle()

            BooksScreen(
                state = booksState,
                onEvent = viewModel::onBookEvent,
                onOpen = { navController.navigate(NavGraph.CurrentBook) },
                onNavigateToAddition = { navController.navigate(NavGraph.AddBook) }
            )
        }
        composable(route = NavGraph.AddBook) {
            val addBookState by viewModel.addBookState.collectAsStateWithLifecycle()

            AddBookScreen(
                state = addBookState,
                onBook = viewModel::onBookEvent,
                onBack = { navController.popBackStack() }
            )
        }
        composable(route = NavGraph.CurrentBook) {
            val bookState by viewModel.currentBookState.collectAsStateWithLifecycle()

            CurrentBookScreen(
                state = bookState,
                onComment = viewModel::onCommentsEvent,
                onBack = { navController.popBackStack() },
                onOpen = { navController.navigate(NavGraph.CurrentAuthor) }
            )
        }
        composable(route = NavGraph.CurrentAuthor) {

        }
    }
}
