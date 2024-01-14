package com.litekreu.bookshelf.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.litekreu.bookshelf.data.ShelfDatabase
import com.litekreu.bookshelf.data.model.AuthorEntity
import com.litekreu.bookshelf.data.model.BookEntity
import com.litekreu.bookshelf.domain.event.BookEvent
import com.litekreu.bookshelf.domain.state.CurrentBookState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val database: ShelfDatabase
) : ViewModel() {
    val booksList = database.booksDao.getAllBooks()
    val authorsList = database.authorsDao.getAllAuthors()

    private val _currentBook = MutableStateFlow(CurrentBookState())
    val currentBook = _currentBook.asStateFlow()

    fun insertBook() {
        viewModelScope.launch {
            try {
                database.booksDao.upsertBook(BookEntity(
                    bookName = "Ферма тварин",
                    bookReleaseYear = 1945,
                    bookDescription = "",
                    bookImageUrl = "https://m.media-amazon.com/images/I/71JUJ6pGoIL._AC_UF1000,1000_QL80_.jpg",
                    authorRefId = 1
                ))
            } catch (e: Exception) {
                insertAuthor()
            }
        }
    }

    private fun insertAuthor() {
        viewModelScope.launch {
            database.authorsDao.insertAuthor(AuthorEntity(
                authorName = "Джордж Орвелл",
                authorImageUrl = "https://hips.hearstapps.com/hmg-prod/images/george-orwell.jpg?crop=1xw:1.0xh;center,top&resize=640:*"
            ))
        }
    }

    fun onBookEvent(event: BookEvent) {
        when(event) {
            is BookEvent.AddBook -> {

            }
            is BookEvent.DeleteBook -> {
                viewModelScope.launch {
                    database.booksDao.deleteBook(event.book)
                }
            }
            is BookEvent.OpenBook -> {
                viewModelScope.launch {
                    _currentBook.update {
                        it.copy(currentBook = event.book)
                    }
                }
            }
        }
    }
}