package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.model.PanelPlan
import com.example.ui.theme.CardStroke
import com.example.ui.theme.CyberCyan
import com.example.ui.theme.DarkBg
import com.example.ui.theme.FireAmber
import com.example.ui.theme.FireOrange
import com.example.ui.theme.FlameGold
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.WhatsAppGreen
import com.example.ui.viewmodel.MainViewModel

@Composable
fun OrderDialog(
    plan: PanelPlan,
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val gamerIgn by viewModel.gamerIgn.collectAsState()
    val gamerUid by viewModel.gamerUid.collectAsState()
    val deviceModel by viewModel.deviceModel.collectAsState()
    val selectedPaymentMethod by viewModel.selectedPaymentMethod.collectAsState()

    val paymentOptions = listOf(
        "Bank Transfer (Commercial / BOC / Sampath)",
        "eZ Cash (075 503 9897)",
        "Dialog / Mobitel Reload (075 503 9897)",
        "FriMi / LankaPay QR",
        "Binance Pay / USDT TRC20"
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .border(1.5.dp, Brush.linearGradient(listOf(FireOrange, CyberCyan)), RoundedCornerShape(20.dp))
                .testTag("order_customization_dialog"),
            colors = CardDefaults.cardColors(containerColor = SurfaceDark),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Title Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "ORDER PAVIN FX PANEL",
                            style = MaterialTheme.typography.titleLarge,
                            color = FireAmber,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Instant WhatsApp Key Activation",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("close_order_dialog")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = TextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Plan Summary Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceElevated)
                        .border(1.dp, CardStroke, RoundedCornerShape(12.dp))
                        .padding(14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = plan.name,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Duration: ${plan.durationDays} Days VIP Access",
                                style = MaterialTheme.typography.bodyMedium,
                                color = CyberCyan
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "${plan.priceLkr} LKR",
                                style = MaterialTheme.typography.headlineMedium,
                                color = FlameGold,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "WhatsApp Admin: 075 503 9897",
                                style = MaterialTheme.typography.labelMedium,
                                color = WhatsAppGreen,
                                fontSize = 11.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Input fields
                Text(
                    text = "Gamer In-Game Name (IGN):",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = gamerIgn,
                    onValueChange = { viewModel.gamerIgn.value = it },
                    placeholder = { Text("e.g. PAVIN_KILLER_99", color = TextMuted) },
                    singleLine = true,
                    leadingIcon = {
                        Icon(Icons.Default.SportsEsports, contentDescription = null, tint = FireAmber)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_gamer_ign"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = FireOrange,
                        unfocusedBorderColor = CardStroke,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Player UID / Account ID:",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = gamerUid,
                    onValueChange = { viewModel.gamerUid.value = it },
                    placeholder = { Text("e.g. 2894729184", color = TextMuted) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_gamer_uid"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = FireOrange,
                        unfocusedBorderColor = CardStroke,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Device Model (for config tuning):",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = deviceModel,
                    onValueChange = { viewModel.deviceModel.value = it },
                    placeholder = { Text("e.g. Poco X3 Pro, Redmi Note 12, Samsung A54", color = TextMuted) },
                    singleLine = true,
                    leadingIcon = {
                        Icon(Icons.Default.PhoneAndroid, contentDescription = null, tint = CyberCyan)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_device_model"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CyberCyan,
                        unfocusedBorderColor = CardStroke,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Payment Method Selector
                Text(
                    text = "Select Payment Method:",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(6.dp))

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    paymentOptions.forEach { method ->
                        val isSelected = selectedPaymentMethod == method
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) SurfaceElevated else DarkBg)
                                .border(
                                    width = if (isSelected) 1.5.dp else 1.dp,
                                    color = if (isSelected) FireAmber else CardStroke,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { viewModel.selectedPaymentMethod.value = method }
                                .padding(horizontal = 12.dp, vertical = 10.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = method,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = if (isSelected) FireAmber else TextSecondary,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                                if (isSelected) {
                                    Icon(
                                        imageVector = Icons.Default.Payment,
                                        contentDescription = null,
                                        tint = FireAmber,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Submit Button
                Button(
                    onClick = {
                        viewModel.submitOrder(plan) { orderMsg ->
                            Toast.makeText(
                                context,
                                "Opening WhatsApp for Pavin FX (075 503 9897)...",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("confirm_whatsapp_order_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhatsAppGreen,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send WhatsApp",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Send Order via WhatsApp (075 503 9897)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Copy Order text button
                OutlinedButton(
                    onClick = {
                        val ref = viewModel.repository.generateOrderRef()
                        val msg = viewModel.repository.buildWhatsAppMessage(
                            plan = plan,
                            gamerIgn = gamerIgn.trim(),
                            gamerUid = gamerUid.trim(),
                            deviceModel = deviceModel.trim(),
                            paymentMethod = selectedPaymentMethod,
                            orderRef = ref
                        )
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        clipboard.setPrimaryClip(ClipData.newPlainText("Pavin FX Order", msg))
                        Toast.makeText(context, "Order slip copied to clipboard!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .testTag("copy_order_slip_button"),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = CyberCyan),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy Slip",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Copy Order Details Slip", fontSize = 13.sp)
                }
            }
        }
    }
}
