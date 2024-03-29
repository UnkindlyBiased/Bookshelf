package com.litekreu.bookshelf.presentation.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.litekreu.bookshelf.R
import com.litekreu.bookshelf.data.model.BookEntity
import com.litekreu.bookshelf.data.model.CommentEntity
import com.litekreu.bookshelf.ui.theme.BookGray
import com.litekreu.bookshelf.ui.theme.googleFamily

@Composable
fun ScreenTitleRow(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(top = 8.dp)) {
        IconButton(onClick = { onBack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                contentDescription = null
            )
        }
        Text(
            text = title,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
        )
    }
}

@Composable
fun BookItem(
    book: BookEntity,
    onDelete: (BookEntity) -> Unit,
    onOpen: (BookEntity) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
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
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = stringResource(R.string.book_release_year)
                                + ": " + book.bookReleaseYear,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(
                    onClick = { onDelete(book) },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Delete"
                    )
                }
            }
        }
    }
}

@Composable
fun InfoRow(
    res: String,
    info: String?,
    modifier: Modifier = Modifier,
    isDecorated: Boolean = false
) {
    Row {
        Text(
            text = "$res ",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = "$info",
            textDecoration = if (isDecorated) TextDecoration.Underline else TextDecoration.None,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = modifier
        )
    }
}

@Composable
fun CommentTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(0.dp)
) {
    Box(modifier = Modifier.padding(padding)) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = BookGray
            ),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxHeight()) {
                    Row(modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 8.dp)) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            tint = BookGray,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        innerTextField().apply {
                            this
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            },
            singleLine = true,
            modifier = modifier
                .fillMaxWidth(0.85f)
                .clip(RoundedCornerShape(20))
                .padding(4.dp)
                .height(48.dp)
        )
    }
}

@Composable
fun CommentsTitle(
    amount: Int?,
    onClick: () -> Unit
) {
    Row(modifier = Modifier
        .clickable {
            onClick()
        }) {
        Text(
            text = stringResource(R.string.comments),
            fontFamily = googleFamily,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = " · ",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Text(
            text = "$amount",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun CommentCard(
    comment: CommentEntity,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row {
            Column(modifier = Modifier
                .weight(1f)
                .padding(12.dp)
                .align(Alignment.CenterVertically)) {
                Text(
                    text = stringResource(R.string.book_progress) + ": ${comment.commentProgress}%",
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = comment.commentText,
                    fontSize = 16.sp
                )
            }
            IconButton(
                onClick = { onDelete() },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}

@Preview
@Composable
fun TextFieldPreview() {
    CommentTextField(value = "Test", onValueChange = {}, padding = PaddingValues())
}

@Preview
@Composable
fun CommentPreview() {
    CommentCard(comment = CommentEntity(commentText = "Some textsadsadasdbsabdkjasbdkbasdbaskbdjasbdasbhldhasldhashdiashdlashidhasildhasidhiashdoiashdipashdioashidhasdhasiphdoashdipashodhasipdhasiopdhipashdipashdpas"), onDelete = { })
}

@Preview
@Composable
fun BookItemPreview() {
    BookItem(book = BookEntity(
        bookName = "TestItem",
        bookReleaseYear = 2023,
        bookDescription = "IDK even",
        bookImageUrl = "",
        authorRefId = 1
    ), onDelete = {}, onOpen = {})
}