package com.litekreu.bookshelf.domain.state

import com.litekreu.bookshelf.data.model.AuthorEntity

data class AuthorsState(
    val authors: List<AuthorEntity> = emptyList()
)
