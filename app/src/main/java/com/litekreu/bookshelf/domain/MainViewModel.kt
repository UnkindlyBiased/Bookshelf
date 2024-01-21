package com.litekreu.bookshelf.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.litekreu.bookshelf.data.ShelfDatabase
import com.litekreu.bookshelf.data.model.AuthorEntity
import com.litekreu.bookshelf.data.model.BookEntity
import com.litekreu.bookshelf.data.model.CommentEntity
import com.litekreu.bookshelf.domain.event.AuthorEvent
import com.litekreu.bookshelf.domain.event.BookEvent
import com.litekreu.bookshelf.domain.event.CommentEvent
import com.litekreu.bookshelf.domain.state.AddBookState
import com.litekreu.bookshelf.domain.state.BooksState
import com.litekreu.bookshelf.domain.state.CurrentAuthorState
import com.litekreu.bookshelf.domain.state.CurrentBookState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val database: ShelfDatabase,
    private val defaultAuthor: AuthorEntity
) : ViewModel() {

    private val _booksState = MutableStateFlow(BooksState())
    val booksState = _booksState.combine(database.booksDao.getAllBooks()) { state, data ->
        state.copy(
            books = data
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(TIMEOUT), BooksState())

    private val authorsList = database.authorsDao.getAllAuthors().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(TIMEOUT), emptyList()
    )

    private val booksList = database.booksDao.getAllBooks().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(TIMEOUT), emptyList()
    )
        
    private val commentsList = database.commentsDao.getAllComments().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(TIMEOUT), emptyList()
    )

    private val _addBookState = MutableStateFlow(AddBookState())
    val addBookState = _addBookState.asStateFlow()

    private val _currentAuthorState = MutableStateFlow(CurrentAuthorState())
    val currentAuthorState = _currentAuthorState.combine(booksList) { state, books ->
        state.copy(
            writtenBooks = books.filter { book ->
                book.authorRefId == state.currentAuthor?.authorId
            }
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(TIMEOUT), null)

    private val _currentBookState = MutableStateFlow(CurrentBookState())
    val currentBookState = combine(_currentBookState, commentsList, authorsList) { state, comments, authors ->
        state.copy(
            bookAuthor = authors.find { author ->
                author.authorId == state.currentBook?.authorRefId
            },
            currentComments = comments.filter { comment ->
                comment.bookRefId == state.currentBook?.id
            }.reversed()
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(TIMEOUT), CurrentBookState())

    fun onAuthorsEvent(event: AuthorEvent) {
        when(event) {
            is AuthorEvent.AddAuthor -> {

            }
            is AuthorEvent.ChooseAuthor -> {
                viewModelScope.launch {
                    _currentAuthorState.update { authorState ->
                        authorState.copy(
                            currentAuthor = event.author
                        )
                    }
                }
            }
            is AuthorEvent.DeleteAuthor -> {

            }
        }
    }
    fun onBookEvent(event: BookEvent) {
        when(event) {
            is BookEvent.AddBook -> {
                viewModelScope.launch {
                    try {
                        database.booksDao.insertBook(BookEntity(
                            bookName = event.title,
                            bookDescription = event.description,
                            bookReleaseYear = event.releaseYear,
                            bookImageUrl = event.imageLink,
                            authorRefId = event.authorId
                        ))
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
                    _currentBookState.update { bookState ->
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
                    database.commentsDao.insertComment(CommentEntity(
                        commentText = event.commentText.replace("\\s+".toRegex(), " "),
                        bookRefId = currentBookState.value.currentBook?.id
                    ))
                }
            }
            is CommentEvent.DeleteComment -> {
                viewModelScope.launch {
                    database.commentsDao.deleteComment(event.comment)
                }
            }
        }
    }

    private companion object {
        private const val TIMEOUT: Long = 3000
    }
}