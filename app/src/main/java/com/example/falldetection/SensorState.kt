package com.example.falldetection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.falldetection.ui.theme.DangerRed
import com.example.falldetection.ui.theme.GuardianGreen
import com.example.falldetection.ui.theme.NavyDark
import java.nio.file.WatchEvent



data class SensorState(
    val personName: String,
    val isSafe: Boolean,
    val statusMessage: String,
    val lastActivity: String,
    val activeSensors: Int,
    val daysNoIncidents: Int
)

@Composable
fun StatusCard(state: SensorState) {
    val backgroundColor = if (state.isSafe) GuardianGreen else DangerRed
    val statusText = if (state.isSafe) "${state.personName} is Safe"
    else "${state.personName} Needs Help!"

    Card(
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
        // ... rest of your card
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
            ) {
                Text(
                    text = state.statusMessage,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyDark
                )
                Text(
                    text = statusText,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyDark
                )
                Text(
                    text = state.lastActivity,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyDark
                )
            }
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Safe status",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}