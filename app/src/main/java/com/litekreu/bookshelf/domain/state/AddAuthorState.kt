package com.litekreu.bookshelf.domain.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class AddAuthorState(
    val authorName: MutableState<String> = mutableStateOf(""),
    val authorImageUrl: MutableState<String> = mutableStateOf("")
)
