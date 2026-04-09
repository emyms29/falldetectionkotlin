package com.example.falldetection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import com.example.falldetection.ui.theme.FallDetectionTheme

// MainActivity.kt
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FallDetectionTheme {
                GuardianSenseApp()
            }
        }
    }   
}

@Composable
fun GuardianSenseApp() {
    var currentScreen by remember { mutableStateOf("login") }

    when (currentScreen) {
        // Uncomment when LoginScreen.kt is ready:
        // "login" -> LoginScreen(onLogin = { currentScreen = "home" })
        else -> MainScaffold()
    }
}

data class NavItem(val route: String, val label: String, val icon: ImageVector)

val navItems = listOf(
    NavItem("home",     "Home",    Icons.Default.Home),
    NavItem("alerts",   "Alerts",  Icons.Default.Notifications),
    NavItem("history",  "History", Icons.Default.DateRange),
    NavItem("devices",  "Devices", Icons.Default.Phone),
    NavItem("settings", "Settings",Icons.Default.Settings)
)

@Composable
fun PlaceholderScreen(name: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "screen coming soon")
    }
}

@Composable
fun MainScaffold(){
    var selectedTab by remember{ mutableStateOf("home") }

    Scaffold(
        bottomBar = {
            NavigationBar{
                navItems.forEach { item ->
                    NavigationBarItem(
                        selected = selectedTab == item.route,
                        onClick = {selectedTab = item.route},
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = {Text(text = item.label, fontSize = 11.sp)}
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                "home"     -> HomeScreen()
                // "alerts"   -> AlertsScreen()
                // "history"  -> HistoryScreen()
                // "devices"  -> DevicesScreen()
                // "settings" -> SettingsScreen()
                else -> PlaceholderScreen(selectedTab)
            }
        }
    }
}