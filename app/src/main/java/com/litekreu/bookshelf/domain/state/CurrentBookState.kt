package com.litekreu.bookshelf.domain.state

import com.litekreu.bookshelf.data.model.BookEntity

data class CurrentBookState(
    val currentBook: BookEntity? = null,
    val reviews: List<String> = emptyList()
)
