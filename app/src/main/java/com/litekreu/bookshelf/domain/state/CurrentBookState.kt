package com.litekreu.bookshelf.domain.state

import com.litekreu.bookshelf.data.model.AuthorEntity
import com.litekreu.bookshelf.data.model.BookEntity
import com.litekreu.bookshelf.data.model.CommentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class CurrentBookState(
    val currentBook: BookEntity? = null,
    val bookAuthor: AuthorEntity? = null,
    val currentComments: List<CommentEntity> = emptyList()
)
