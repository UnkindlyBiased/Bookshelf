package com.litekreu.bookshelf.presentation.elements

import androidx.annotation.StringRes
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
                        fontWeight = FontWeight.Medium,
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

@Composable
fun InfoRow(
    @StringRes res: Int,
    info: String?,
    modifier: Modifier = Modifier
) {
    Row {
        Text(
            text = stringResource(res) + " ",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$info",
            modifier = modifier
        )
    }
}