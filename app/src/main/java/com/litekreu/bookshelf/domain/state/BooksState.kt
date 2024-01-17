package com.litekreu.bookshelf.domain.state

import com.litekreu.bookshelf.data.model.BookEntity

data class BooksState(
    val books: List<BookEntity> = emptyList()
)
