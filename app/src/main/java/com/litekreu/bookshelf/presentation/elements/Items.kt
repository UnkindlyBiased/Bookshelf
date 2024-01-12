package com.litekreu.bookshelf.presentation.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.litekreu.bookshelf.data.model.BookEntity

@Composable
fun BookItem(
    book: BookEntity,
    onDelete: (BookEntity) -> Unit,
    onOpen: (BookEntity) -> Unit
) {
    Card {
        Box(modifier = Modifier.clickable { onOpen(book) }) {
            Row(modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()) {
                AsyncImage(
                    model = book.bookImageUrl,
                    contentDescription = book.bookName,
                    modifier = Modifier.size(76.dp)
                )
                Column(modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)) {
                    Text(
                        text = book.bookName,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(text = "Рік видавництва: ${book.bookReleaseYear}")
                }
                IconButton(
                    onClick = { onDelete(book) },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
        }
    }
}

@SuppressLint("NewApi")
@Preview
@Composable
fun BookPreview() {
    BookItem(book = BookEntity(
        bookName = "Ферма тварин",
        bookReleaseYear = 1945,
        bookDescription = "Чудова книга",
        bookImpressions = "Книга чудова",
        bookImageUrl = "https://example.com/image.url",
        authorRefId = 1
    ), onDelete = {  }, onOpen = {  })
}