package com.litekreu.bookshelf.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.litekreu.bookshelf.R
import com.litekreu.bookshelf.domain.event.CommentEvent
import com.litekreu.bookshelf.domain.state.CurrentBookState
import com.litekreu.bookshelf.presentation.elements.InfoRow

@Composable
fun CurrentBookScreen(
    state: CurrentBookState?,
    onBack: () -> Unit,
    onComment: (CommentEvent) -> Unit
) {
    LazyColumn {
        item {
            Row(modifier = Modifier.padding(top = 8.dp)) {
                val rowColor = Color.Black.copy(alpha = 0.65f)
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = rowColor,
                        contentDescription = null
                    )
                }
                state?.currentBook?.let {
                    Text(
                        text = it.bookName,
                        fontSize = 18.sp,
                        color = rowColor,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(1f)
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = rowColor
                    )
                }
            }
        }
        item {
            Row(modifier = Modifier.padding(top = 16.dp, start = 24.dp)) {
                state?.currentBook?.let {
                    AsyncImage(
                        model = it.bookImageUrl,
                        contentDescription = null,
                        modifier = Modifier.height(172.dp)
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 12.dp)
                    ) {
                        Text(
                            text = it.bookName,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Column(modifier = Modifier.padding(top = 14.dp)) {
                            InfoRow(res = R.string.release_year, info = "${it.bookReleaseYear}")
                            InfoRow(
                                res = R.string.author,
                                info = state.bookAuthor?.authorName,
                                modifier = Modifier.clickable {

                                }
                            )
                        }
                    }
                }
            }
        }
        state?.let {
            items(it.currentComments) { comment ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)) {
                        Text(text = "${comment.id}")
                        Text(
                            text = comment.commentText,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    IconButton(onClick = { onComment(CommentEvent.DeleteComment(comment)) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null
                        )
                    }
                }
            }
        }
        item {
            Button(onClick = { onComment(CommentEvent.AddComment()) }) {
                Text(text = stringResource(R.string.add_comment))
            }
        }
    }
}