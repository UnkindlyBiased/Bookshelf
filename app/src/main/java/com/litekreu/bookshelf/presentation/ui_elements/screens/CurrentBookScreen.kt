package com.litekreu.bookshelf.presentation.ui_elements.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.litekreu.bookshelf.presentation.ui_elements.elements.CommentCard
import com.litekreu.bookshelf.presentation.ui_elements.elements.CommentTextField
import com.litekreu.bookshelf.presentation.ui_elements.elements.CommentsTitle
import com.litekreu.bookshelf.presentation.ui_elements.elements.InfoRow
import com.litekreu.bookshelf.presentation.ui_elements.elements.ScreenTitleRow
import com.litekreu.bookshelf.ui.theme.BookGray
import com.litekreu.bookshelf.ui.theme.googleFamily
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentBookScreen(
    state: CurrentBookState?,
    onBack: () -> Unit,
    onComment: (CommentEvent) -> Unit,
    onOpen: () -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()
    var showBottomSheet by rememberSaveable {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    if (showBottomSheet) {
        ModalBottomSheet(onDismissRequest = { scope.launch {
            showBottomSheet = false
        } },
            sheetState = bottomSheetState,
            modifier = Modifier.fillMaxHeight(0.80f)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    CommentsTitle(amount = state?.currentComments?.size) { }
                }
                state?.let { state ->
                    item {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(15))
                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                .padding(16.dp)
                        ) {
                            Column {
                                Text(
                                    text = stringResource(R.string.add_comment),
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Row {
                                    CommentTextField(
                                        value = state.commentText.value,
                                        onValueChange = { state.commentText.value = it },
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(16.dp))
                                            .background(Color.Black.copy(alpha = 0.1f))
                                            .weight(10f)
                                    )
                                    IconButton(
                                        onClick = { state.commentText.value.also {
                                            if (it.isNotBlank()) {
                                                onComment(CommentEvent.AddComment(it))
                                            }
                                        } },
                                        modifier = Modifier
                                            .weight(1f)
                                            .align(Alignment.CenterVertically)
                                    ) {
                                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                state.bookProgress.also { floatState ->
                                    Column {
                                        Text(
                                            text = stringResource(R.string.book_progress),
                                            style = MaterialTheme.typography.titleSmall
                                        )
                                        Row {
                                            Slider(
                                                value = floatState.floatValue,
                                                valueRange = 0f..100f,
                                                onValueChange = { floatState.floatValue = it },
                                                colors = SliderDefaults.colors(
                                                    inactiveTrackColor = MaterialTheme.colorScheme.outline
                                                ),
                                                modifier = Modifier.weight(6f),
                                                steps = 0
                                            )
                                            Text(
                                                text = "${floatState.floatValue.toInt()}%",
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .align(Alignment.CenterVertically)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    items(state.currentComments) { comment ->
                        CommentCard(comment = comment, onDelete = { onComment(CommentEvent.DeleteComment(comment)) })
                    }
                }
            }
        }
    }
    LazyColumn {
        item {
            Row {
                state?.currentBook?.bookName?.let {
                    ScreenTitleRow(
                        title = it,
                        onBack = { onBack() },
                        modifier = Modifier.weight(1f)
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(top = 8.dp, end = 8.dp)
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
                            InfoRow(res = R.string.book_release_year, info = "${it.bookReleaseYear}")
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
                Row {
                    CommentsTitle(amount = state?.currentComments?.size) {
                        scope.launch {
                            showBottomSheet = true
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.beta_comments_box),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.padding(top = 24.dp)
                    )
                }
            }
        }
    }
}