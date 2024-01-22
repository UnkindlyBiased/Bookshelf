package com.litekreu.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.litekreu.bookshelf.presentation.ui_elements.screens.MainScreen
import com.litekreu.bookshelf.ui.theme.BookshelfTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            BookshelfTheme {
                MainScreen()
            }
        }
    }
}

