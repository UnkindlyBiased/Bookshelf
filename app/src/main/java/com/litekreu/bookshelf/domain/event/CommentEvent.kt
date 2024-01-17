package com.litekreu.bookshelf.domain.event

import com.litekreu.bookshelf.data.model.CommentEntity

sealed interface CommentEvent {
    data class AddComment(
        val test: Int? = null
    ) : CommentEvent
    data class DeleteComment(val comment: CommentEntity): CommentEvent
}