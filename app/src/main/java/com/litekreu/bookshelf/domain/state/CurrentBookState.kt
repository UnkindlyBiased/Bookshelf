package com.litekreu.bookshelf.domain.state

import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.litekreu.bookshelf.data.model.AuthorEntity
import com.litekreu.bookshelf.data.model.BookEntity
import com.litekreu.bookshelf.data.model.CommentEntity

data class CurrentBookState(
    val currentBook: BookEntity? = null,
    val bookAuthor: AuthorEntity? = null,
    val currentComments: List<CommentEntity> = emptyList(),
    val commentText: MutableState<String> = mutableStateOf(""),
    val bookProgress: MutableFloatState = mutableFloatStateOf(0f)
)
