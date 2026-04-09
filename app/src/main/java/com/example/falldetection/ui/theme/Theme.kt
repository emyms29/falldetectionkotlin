package com.example.falldetection.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// ── Light color scheme ─────────────────────────────────────────────────────────

private val LightColorScheme = lightColorScheme(
    primary           = AccentBlue,
    onPrimary         = Color.White,
    primaryContainer  = Color(0xFFEEF2FF),
    onPrimaryContainer= AccentBlue,

    secondary         = GuardianGreen,
    onSecondary       = Color.White,
    secondaryContainer= Color(0xFFDCFCE7),
    onSecondaryContainer = Color(0xFF166534),

    tertiary          = WarningAmber,
    onTertiary        = Color.White,

    error             = DangerRed,
    onError           = Color.White,

    background        = SurfaceGray,
    onBackground      = NavyDark,

    surface           = CardWhite,
    onSurface         = NavyDark,
    surfaceVariant    = Color(0xFFE8EAF0),
    onSurfaceVariant  = TextMuted,

    outline           = Color(0xFFE0E0E0)
)
private val DarkColorScheme = darkColorScheme(
    primary           = Color(0xFF5B8DEE),
    onPrimary         = Color.White,
    primaryContainer  = Color(0xFF1B4FD8),
    onPrimaryContainer= Color.White,

    secondary         = SafeGreen,
    onSecondary       = NavyDark,
    secondaryContainer= Color(0xFF1B7A3E),
    onSecondaryContainer = Color(0xFF86EFAC),

    tertiary          = WarningAmber,
    onTertiary        = NavyDark,

    error             = Color(0xFFF87171),
    onError           = NavyDark,

    background        = NavyDarkBg,
    onBackground      = Color.White,

    surface           = NavyDarkSurface,
    onSurface         = Color.White,
    surfaceVariant    = Color(0xFF1A2E48),
    onSurfaceVariant  = Color(0xFF8A9BB5),

    outline           = Color(0xFF2A3A50)
)

@Composable
fun FallDetectionTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else      -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = NavyDark.toArgb()
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = Typography,
        content     = content
    )
}