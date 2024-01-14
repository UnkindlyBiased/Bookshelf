package com.litekreu.bookshelf.domain.event

import com.litekreu.bookshelf.data.model.BookEntity

sealed interface BookEvent {
    data class AddBook(
        val title: String,
        val description: String,
        val authorId: Int
    ): BookEvent
    data class DeleteBook(val book: BookEntity) : BookEvent
    data class OpenBook(val book: BookEntity): BookEvent
}