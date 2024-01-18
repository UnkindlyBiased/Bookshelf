package com.litekreu.bookshelf.domain.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.litekreu.bookshelf.data.model.AuthorEntity
import com.litekreu.bookshelf.data.model.BookEntity
import com.litekreu.bookshelf.data.model.CommentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class CurrentBookState(
    val currentBook: BookEntity? = null,
    val bookAuthor: AuthorEntity? = null,
    val currentComments: List<CommentEntity> = emptyList(),
    val commentText: MutableState<String> = mutableStateOf("")
)
