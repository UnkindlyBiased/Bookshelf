package com.litekreu.bookshelf.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.litekreu.bookshelf.domain.ShelfViewModel

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: ShelfViewModel = hiltViewModel()
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
            CurrentBookScreen(
                viewModel = viewModel,
                onComment = viewModel::onCommentsEvent,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
