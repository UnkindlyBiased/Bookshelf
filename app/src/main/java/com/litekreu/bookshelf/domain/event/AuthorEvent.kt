package com.litekreu.bookshelf.domain.event

import com.litekreu.bookshelf.data.model.AuthorEntity

sealed interface AuthorEvent {
    data class AddAuthor(val author: AuthorEntity): AuthorEvent
    data class ChooseAuthor(val author: AuthorEntity): AuthorEvent
    data class DeleteAuthor(val author: AuthorEntity): AuthorEvent
}