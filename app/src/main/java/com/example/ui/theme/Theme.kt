package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = FireOrange,
    onPrimary = Color.White,
    primaryContainer = SurfaceElevated,
    onPrimaryContainer = FireAmber,
    secondary = CyberCyan,
    onSecondary = Color.Black,
    secondaryContainer = SurfaceElevated,
    onSecondaryContainer = CyberCyan,
    tertiary = FlameGold,
    onTertiary = Color.Black,
    background = DarkBg,
    onBackground = TextPrimary,
    surface = SurfaceDark,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceDarkVariant,
    onSurfaceVariant = TextSecondary,
    outline = CardStroke
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    // Pavin FX is dedicated to an intense dark esports gaming aesthetic
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
