package com.litekreu.bookshelf.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.litekreu.bookshelf.data.ShelfDatabase
import com.litekreu.bookshelf.data.model.AuthorEntity
import com.litekreu.bookshelf.data.model.BookEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val database: ShelfDatabase
) : ViewModel() {
    val booksList = database.booksDao.getAllBooks()
    val authorsList = database.authorsDao.getAllAuthors()

    val currentBook = MutableStateFlow("")

    fun insertBook() {
        viewModelScope.launch {
            database.booksDao.upsertBook(BookEntity(
                bookName = "Ферма тварин",
                bookReleaseYear = 1945,
                bookDescription = "",
                bookImageUrl = "https://m.media-amazon.com/images/I/71JUJ6pGoIL._AC_UF1000,1000_QL80_.jpg",
                authorRefId = 1
            ))
        }
    }

    fun insertAuthor() {
        viewModelScope.launch {
            database.authorsDao.insertAuthor(AuthorEntity(
                authorName = "Джордж Орвелл",
                authorImageUrl = "https://hips.hearstapps.com/hmg-prod/images/george-orwell.jpg?crop=1xw:1.0xh;center,top&resize=640:*"
            ))
        }
    }
}