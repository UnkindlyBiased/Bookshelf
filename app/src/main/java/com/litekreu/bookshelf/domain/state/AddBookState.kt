package com.litekreu.bookshelf.domain.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class AddBookState(
    val bookName: MutableState<String> = mutableStateOf(""),
    // TODO: Idk why it's String if it then will be converted to Int
    val bookReleaseYear: MutableState<String> = mutableStateOf(""),
    val bookDescription: MutableState<String> = mutableStateOf(""),
    val bookImageUrl: MutableState<String> = mutableStateOf("")
)
