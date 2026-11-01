package com.example.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.OrderDialog
import com.example.ui.components.PavinTopBar
import com.example.ui.screens.ActivatorScreen
import com.example.ui.screens.OrdersScreen
import com.example.ui.screens.PaymentScreen
import com.example.ui.screens.SensCalculatorScreen
import com.example.ui.screens.StoreScreen
import com.example.ui.theme.CardStroke
import com.example.ui.theme.CyberCyan
import com.example.ui.theme.DarkBg
import com.example.ui.theme.FireAmber
import com.example.ui.theme.FireOrange
import com.example.ui.theme.FlameGold
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.WhatsAppGreen
import com.example.ui.viewmodel.AppNavTab
import com.example.ui.viewmodel.MainViewModel

@Composable
fun PavinFxApp(viewModel: MainViewModel = viewModel()) {
    val context = LocalContext.current
    val currentTab by viewModel.currentTab.collectAsState()
    val activeLicense by viewModel.activeLicense.collectAsState()
    val orderPlan by viewModel.orderPlan.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = DarkBg,
        topBar = {
            PavinTopBar(
                activeLicense = activeLicense,
                onWhatsAppClick = {
                    viewModel.repository.launchWhatsApp(
                        "Hello Pavin FX! I want to inquire about purchasing the 31-day Free Fire VIP panel (3500 LKR)."
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.repository.launchWhatsApp(
                        "Hello Pavin FX! I would like to buy the 31 Day VIP Panel (3500 LKR). Please provide payment instructions."
                    )
                },
                containerColor = WhatsAppGreen,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier
                    .testTag("fab_whatsapp_hotline")
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = "WhatsApp Hotline 075 503 9897",
                    modifier = Modifier.size(26.dp)
                )
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = SurfaceDark,
                modifier = Modifier
                    .navigationBarsPadding()
                    .border(1.dp, CardStroke, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            ) {
                NavigationBarItem(
                    selected = currentTab == AppNavTab.STORE,
                    onClick = { viewModel.setTab(AppNavTab.STORE) },
                    icon = { Icon(Icons.Default.ShoppingBag, contentDescription = "VIP Store") },
                    label = { Text("Store", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = navItemColors(),
                    modifier = Modifier.testTag("nav_tab_store")
                )
                NavigationBarItem(
                    selected = currentTab == AppNavTab.ACTIVATOR,
                    onClick = { viewModel.setTab(AppNavTab.ACTIVATOR) },
                    icon = { Icon(Icons.Default.Key, contentDescription = "Key Activator") },
                    label = { Text("Activator", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = navItemColors(),
                    modifier = Modifier.testTag("nav_tab_activator")
                )
                NavigationBarItem(
                    selected = currentTab == AppNavTab.SENSITIVITY,
                    onClick = { viewModel.setTab(AppNavTab.SENSITIVITY) },
                    icon = { Icon(Icons.Default.Tune, contentDescription = "Sens Tool") },
                    label = { Text("Sens Tool", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = navItemColors(),
                    modifier = Modifier.testTag("nav_tab_sensitivity")
                )
                NavigationBarItem(
                    selected = currentTab == AppNavTab.PAYMENT,
                    onClick = { viewModel.setTab(AppNavTab.PAYMENT) },
                    icon = { Icon(Icons.Default.AccountBalanceWallet, contentDescription = "Payments") },
                    label = { Text("Payments", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = navItemColors(),
                    modifier = Modifier.testTag("nav_tab_payments")
                )
                NavigationBarItem(
                    selected = currentTab == AppNavTab.ORDERS,
                    onClick = { viewModel.setTab(AppNavTab.ORDERS) },
                    icon = { Icon(Icons.Default.ReceiptLong, contentDescription = "My Orders") },
                    label = { Text("Orders", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = navItemColors(),
                    modifier = Modifier.testTag("nav_tab_orders")
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentTab) {
                AppNavTab.STORE -> StoreScreen(
                    viewModel = viewModel,
                    onNavigateToPayments = { viewModel.setTab(AppNavTab.PAYMENT) }
                )
                AppNavTab.ACTIVATOR -> ActivatorScreen(viewModel = viewModel)
                AppNavTab.SENSITIVITY -> SensCalculatorScreen(viewModel = viewModel)
                AppNavTab.PAYMENT -> PaymentScreen(viewModel = viewModel)
                AppNavTab.ORDERS -> OrdersScreen(viewModel = viewModel)
            }

            // Order customization dialog if active
            orderPlan?.let { plan ->
                OrderDialog(
                    plan = plan,
                    viewModel = viewModel,
                    onDismiss = { viewModel.dismissOrderDialog() }
                )
            }
        }
    }
}

@Composable
private fun navItemColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = FireOrange,
    selectedTextColor = FireOrange,
    unselectedIconColor = TextMuted,
    unselectedTextColor = TextMuted,
    indicatorColor = FireOrange.copy(alpha = 0.15f)
)
