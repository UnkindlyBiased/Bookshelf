package com.litekreu.bookshelf.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.litekreu.bookshelf.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val playfairDisplay = GoogleFont("Playfair Display")

val PlayfairDisplay = FontFamily(
    Font(playfairDisplay, provider)
)

val googleFamily = FontFamily(
    androidx.compose.ui.text.font.Font(R.font.google_regular, FontWeight.Normal),
    androidx.compose.ui.text.font.Font(R.font.google_medium, FontWeight.Medium),
    androidx.compose.ui.text.font.Font(R.font.google_bold, FontWeight.Bold),
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = googleFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = googleFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = googleFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)