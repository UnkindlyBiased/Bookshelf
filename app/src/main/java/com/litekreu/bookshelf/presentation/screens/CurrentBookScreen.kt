package com.litekreu.bookshelf.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.litekreu.bookshelf.domain.MainViewModel

@Composable
fun CurrentBookScreen(
    viewModel: MainViewModel,
    onBack: () -> Unit
) {
    val currentBookState by viewModel.currentBook.collectAsStateWithLifecycle()

    Column {
        Row(modifier = Modifier.padding(top = 8.dp)) {
            val rowColor = Color.Black.copy(alpha = 0.65f)
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = rowColor,
                    contentDescription = null
                )
            }
            currentBookState.currentBook?.let { Text(
                text = it.bookName,
                fontSize = 18.sp,
                color = rowColor,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) }
        }
        Row(modifier = Modifier.padding(top = 16.dp, start = 24.dp)) {
            currentBookState.currentBook?.let {
                AsyncImage(
                    model = it.bookImageUrl,
                    contentDescription = null,
                    modifier = Modifier.height(172.dp)
                )
                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                    Text(
                        text = it.bookName,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            }
        }
    }
}