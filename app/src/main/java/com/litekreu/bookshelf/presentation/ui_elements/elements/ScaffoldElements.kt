@file:OptIn(ExperimentalMaterial3Api::class)

package com.litekreu.bookshelf.presentation.ui_elements.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.litekreu.bookshelf.R
import com.litekreu.bookshelf.ui.theme.PlayfairDisplay

@Composable
fun ShelfTopBar() {
    Box(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(title = {
            Text(
                text = stringResource(R.string.app_name),
                fontFamily = PlayfairDisplay,
                fontSize = 24.sp,
                modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
            )
        }, modifier = Modifier.drawBehind {
            drawRoundRect(color = Color.Red, cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx()))
        })
    }
}