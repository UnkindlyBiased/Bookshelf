package com.litekreu.bookshelf.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.litekreu.bookshelf.R
import com.litekreu.bookshelf.domain.event.CommentEvent
import com.litekreu.bookshelf.domain.state.CurrentBookState
import com.litekreu.bookshelf.presentation.elements.CommentCard
import com.litekreu.bookshelf.presentation.elements.CommentTextField
import com.litekreu.bookshelf.presentation.elements.InfoRow
import com.litekreu.bookshelf.ui.theme.BookGray
import com.litekreu.bookshelf.ui.theme.googleFamily

@Composable
fun CurrentBookScreen(
    state: CurrentBookState?,
    onBack: () -> Unit,
    onComment: (CommentEvent) -> Unit,
    onOpen: () -> Unit
) {
    LazyColumn {
        item {
            Row(modifier = Modifier.padding(top = 8.dp)) {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = BookGray,
                        contentDescription = null
                    )
                }
                state?.currentBook?.let {
                    Text(
                        text = it.bookName,
                        fontSize = 18.sp,
                        color = BookGray,
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
                        tint = BookGray
                    )
                }
            }
        }
        item {
            Row(modifier = Modifier.padding(top = 16.dp, start = 16.dp)) {
                state?.currentBook?.let {
                    AsyncImage(
                        model = it.bookImageUrl,
                        contentDescription = null,
                        modifier = Modifier.height(172.dp)
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 16.dp)
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
                                isDecorated = true,
                                modifier = Modifier.clickable {
                                    onOpen()
                                }
                            )
                        }
                    }
                }
            }
        }
        item {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(R.string.book_desc),
                    fontFamily = googleFamily,
                    style = MaterialTheme.typography.titleLarge
                )
                SelectionContainer {
                    Text(
                        text = "${state?.currentBook?.bookDescription}",
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Row(modifier = Modifier.padding(top = 18.dp)) {
                    Text(
                        text = stringResource(R.string.comments),
                        fontFamily = googleFamily,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = " · ",
                        style = MaterialTheme.typography.titleLarge,
                        color = BookGray
                    )
                    Text(
                        text = "${state?.currentComments?.size}",
                        style = MaterialTheme.typography.titleLarge,
                        color = BookGray
                    )
                }
            }
        }
        state?.let {
            items(it.currentComments) { comment ->
                CommentCard(
                    comment = comment,
                    onDelete = { onComment(CommentEvent.DeleteComment(comment)) },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
        item {
            CommentTextField(value = "${state?.commentText?.value}", onValueChange = {
                state?.commentText?.value = it
            },
                modifier = Modifier.clip(RoundedCornerShape(16.dp)).background(Color.Black.copy(alpha = 0.1f)),
                padding = PaddingValues(16.dp)
            )
        }
        item {
            state?.commentText?.let { textState ->
                Button(onClick = {
                    onComment(CommentEvent.AddComment(textState.value))
                    textState.value = ""
                },
                    modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = stringResource(R.string.add_comment),
                        fontFamily = googleFamily
                    )
                }
            }
        }
    }
}