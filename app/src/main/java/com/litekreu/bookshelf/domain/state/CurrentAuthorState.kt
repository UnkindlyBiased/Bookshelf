package com.litekreu.bookshelf.domain.state

import com.litekreu.bookshelf.data.model.AuthorEntity
import com.litekreu.bookshelf.data.model.BookEntity

data class CurrentAuthorState(
    val currentAuthor: AuthorEntity? = null,
    val writtenBooks: List<BookEntity> = emptyList()
)