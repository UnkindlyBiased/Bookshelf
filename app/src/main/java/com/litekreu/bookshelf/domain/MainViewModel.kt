package com.litekreu.bookshelf.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.litekreu.bookshelf.data.ShelfDatabase
import kotlinx.coroutines.launch

class MainViewModel(
    private val database: ShelfDatabase
) : ViewModel() {
    val booksList = database.booksDao.getAllBooks()

    fun insertBook() {
        viewModelScope.launch {

        }
    }

    fun insertAuthor() {
        viewModelScope.launch {

        }
    }
}