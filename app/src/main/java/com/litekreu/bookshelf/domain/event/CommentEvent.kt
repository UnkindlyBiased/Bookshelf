package com.litekreu.bookshelf.domain.event

import com.litekreu.bookshelf.data.model.CommentEntity

sealed interface CommentEvent {
    data class AddComment(
        val commentText: String,
        val bookRefId: Int? = null
    ) : CommentEvent
    data class DeleteComment(val comment: CommentEntity): CommentEvent
}