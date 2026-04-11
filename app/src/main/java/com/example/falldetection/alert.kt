package com.example.falldetection

import androidx.compose.ui.graphics.Color
import com.example.falldetection.ui.theme.DangerRed
import com.example.falldetection.ui.theme.InfoBlue
import com.example.falldetection.ui.theme.WarningAmber

enum class AlertSeverity {
    CRITICAL, WARNING, INFO
}

data class Alert(
    val id: Int,
    val title: String,
    val description: String,
    val time: String,
    val severity: AlertSeverity,
    val isRead: Boolean = false
)

fun AlertSeverity.color(): Color = when (this) {
    AlertSeverity.CRITICAL -> DangerRed
    AlertSeverity.WARNING  -> WarningAmber
    AlertSeverity.INFO     -> InfoBlue
}

fun AlertSeverity.label(): String = when (this) {
    AlertSeverity.CRITICAL -> "Critical"
    AlertSeverity.WARNING  -> "Warning"
    AlertSeverity.INFO     -> "Info"
}

val sampleAlerts = listOf(
    Alert(1, "Unusual Inactivity Detected", "No movement in living room for 2 hours", "9:15 AM", AlertSeverity.WARNING),
    Alert(2, "Sensor Battery Low", "Living room sensor at 15% battery", "8:42 AM", AlertSeverity.WARNING),
    Alert(3, "System Check Complete", "All sensors responded normally", "7:00 AM", AlertSeverity.INFO),
    Alert(4, "Night Movement Detected", "Activity detected at 2:30 AM", "2:30 AM", AlertSeverity.INFO),
    Alert(5, "App Updated", "GuardianSense updated to v2.4.1", "Yesterday", AlertSeverity.INFO)
)