package com.example.falldetection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.falldetection.ui.theme.NavyDark
import com.example.falldetection.ui.theme.NavyDarkBg
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
            .verticalScroll(rememberScrollState())
    ){
        Box(
            modifier = Modifier
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
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = null
                    )
                }
            }
        }
        StatusCard(state = sensorState)
    }
}