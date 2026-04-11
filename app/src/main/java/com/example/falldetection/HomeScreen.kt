package com.example.falldetection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.falldetection.ui.theme.AccentBlue
import com.example.falldetection.ui.theme.CardWhite
import com.example.falldetection.ui.theme.GuardianGreen
import com.example.falldetection.ui.theme.NavyDark
import com.example.falldetection.ui.theme.NavyDarkBg
import com.example.falldetection.ui.theme.SurfaceGray
import com.example.falldetection.ui.theme.TextMuted


@Composable

fun HomeScreen(){
    var sensorState by remember {
        mutableStateOf(
            SensorState(
                personName = "Mom",
                isSafe = true,
                statusMessage = "All Systems Active",
                lastActivity = "Last activity: 3 min ago",
                activeSensors = 3,
                daysNoIncidents = 14
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceGray)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ){
        Row(){
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ){
                    Text(
                        text = "GuardianSense",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = NavyDark
                    )
                    Text(
                        text = "Smart Fall Detection",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        color = TextMuted
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = null
                    )
                }
            }
        Spacer(modifier = Modifier.height(16.dp))
        StatusCard(state = sensorState)
        Spacer(modifier = Modifier.height(16.dp))
        StatRow(state = sensorState)
    }
}

@Composable
fun StatRow(state: SensorState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Filled.Home,
            value = state.activeSensors.toString(),
            label = "Active Sensors"
        )
        StatCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Filled.CheckCircle,
            value = state.daysNoIncidents.toString(),
            label = "Days No Incidents",
            iconTint = AccentBlue
        )
    }
}

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    value: String,
    label: String,
    iconTint: Color = AccentBlue
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(iconTint.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = NavyDark)
            Text(text = label, fontSize = 12.sp, color = TextMuted)
        }
    }
}
