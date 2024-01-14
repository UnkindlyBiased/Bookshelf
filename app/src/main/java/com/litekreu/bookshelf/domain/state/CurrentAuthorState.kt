package com.litekreu.bookshelf.domain.state

import com.litekreu.bookshelf.data.model.AuthorEntity

data class CurrentAuthorState(
    val currentAuthor: AuthorEntity? = null
)