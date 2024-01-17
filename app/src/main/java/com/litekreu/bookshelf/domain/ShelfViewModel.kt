package com.litekreu.bookshelf.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.litekreu.bookshelf.data.ShelfDatabase
import com.litekreu.bookshelf.data.model.AuthorEntity
import com.litekreu.bookshelf.data.model.BookEntity
import com.litekreu.bookshelf.data.model.CommentEntity
import com.litekreu.bookshelf.domain.event.BookEvent
import com.litekreu.bookshelf.domain.event.CommentEvent
import com.litekreu.bookshelf.domain.state.CurrentBookState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShelfViewModel @Inject constructor(
    private val database: ShelfDatabase,
    private val defaultAuthor: AuthorEntity,
    private val defaultBook: BookEntity,
    private val defaultComment: CommentEntity
) : ViewModel() {
    val booksList = database.booksDao.getAllBooks()
    private var commentsList = database.commentsDao.getAllComments().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(), emptyList()
    )

    private val _currentBook = MutableStateFlow(CurrentBookState())
    val currentBook = combine(_currentBook, commentsList) { state, comments ->
        state.copy(
            currentComments = comments.filter { comment ->
                comment.bookRefId == state.currentBook?.id
            }
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun onBookEvent(event: BookEvent) {
        when(event) {
            is BookEvent.AddBook -> {
                viewModelScope.launch {
                    try {
                        database.booksDao.insertBook(defaultBook)
                    } catch (e: Exception) {
                        database.authorsDao.insertAuthor(defaultAuthor)
                    }
                }
            }
            is BookEvent.DeleteBook -> {
                viewModelScope.launch {
                    database.booksDao.deleteBook(event.book)
                }
            }
            is BookEvent.OpenBook -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _currentBook.update { bookState ->
                        bookState.copy(
                            currentBook = event.book
                        )
                    }
                }
            }
        }
    }
    fun onCommentsEvent(event: CommentEvent) {
        when(event) {
            is CommentEvent.AddComment -> {
                viewModelScope.launch {
                    database.commentsDao.insertComment(defaultComment)
                }
            }
            is CommentEvent.DeleteComment -> {
                viewModelScope.launch {
                    database.commentsDao.deleteComment(event.comment)
                }
            }
        }
    }
}