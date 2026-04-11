package com.example.falldetection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.falldetection.ui.theme.*

@Composable
fun AlertSummaryChip(
    count: Int,
    label: String,
    color: androidx.compose.ui.graphics.Color
) {
    Card(
        modifier = Modifier.width(100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = count.toString(),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Text(
                text = label,
                fontSize = 12.sp,
                color = TextMuted
            )
        }
    }
}

@Composable
fun AlertSummaryRow(alerts: List<Alert>) {
    val critical = alerts.count { it.severity == AlertSeverity.CRITICAL }
    val warnings = alerts.count { it.severity == AlertSeverity.WARNING }
    val info     = alerts.count { it.severity == AlertSeverity.INFO }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AlertSummaryChip(count = critical, label = "Critical", color = DangerRed)
        AlertSummaryChip(count = warnings, label = "Warnings", color = WarningAmber)
        AlertSummaryChip(count = info,     label = "Info",     color = InfoBlue)
    }
}

@Composable
fun NoEmergencyBanner() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = null,
                    tint = GuardianGreen,
                    modifier = Modifier.size(28.dp)
                )
                Column {
                    Text(
                        text = "No Active Emergencies",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = NavyDark
                    )
                    Text(
                        text = "All clear as of 9:41 AM",
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                }
            }
            // "Safe" badge on the right
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(GuardianGreen.copy(alpha = 0.15f))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Safe",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = GuardianGreen
                )
            }
        }
    }
}

@Composable
fun AlertItem(alert: Alert) {
    val severityColor = alert.severity.color()
    val severityIcon = when (alert.severity) {
        AlertSeverity.CRITICAL -> Icons.Filled.Warning
        AlertSeverity.WARNING  -> Icons.Filled.Warning
        AlertSeverity.INFO     -> Icons.Filled.Info
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {

            // Colored left border strip
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(80.dp)
                    .background(severityColor)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Icon + text
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(severityColor.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = severityIcon,
                            contentDescription = null,
                            tint = severityColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Column {
                        Text(
                            text = alert.title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = NavyDark
                        )
                        Text(
                            text = alert.description,
                            fontSize = 12.sp,
                            color = TextMuted,
                            maxLines = 1
                        )
                        Text(
                            text = alert.time,
                            fontSize = 11.sp,
                            color = TextMuted.copy(alpha = 0.7f)
                        )
                    }
                }

                // Chevron
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = null,
                    tint = TextMuted,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun AlertsScreen() {
    val alerts = remember { sampleAlerts }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceGray)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Alerts",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyDark
                )
                Text(
                    text = "Notifications & Warnings",
                    fontSize = 12.sp,
                    color = TextMuted
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.FilterList,
                    contentDescription = "Filter",
                    tint = NavyDark
                )
            }
        }

        // Summary chips
        AlertSummaryRow(alerts = alerts)

        // No emergency banner
        NoEmergencyBanner()

        // Today's alerts list
        Text(
            text = "TODAY",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = TextMuted,
            letterSpacing = 1.5.sp
        )

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            alerts.forEach { alert ->
                AlertItem(alert = alert)
            }
        }
    }
}