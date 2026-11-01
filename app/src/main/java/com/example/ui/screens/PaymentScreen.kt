package com.example.ui.screens

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun PaymentScreen(viewModel: MainViewModel) {
    val context = LocalContext.current

    fun copyToClipboard(label: String, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText(label, text))
        Toast.makeText(context, "$label copied: $text", Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
            .testTag("payment_screen")
    ) {
        Spacer(modifier = Modifier.height(14.dp))

        // Header Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.linearGradient(
                        listOf(SurfaceDark, SurfaceElevated)
                    )
                )
                .border(1.dp, Brush.horizontalGradient(listOf(FireOrange, CyberCyan)), RoundedCornerShape(16.dp))
                .padding(18.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CurrencyExchange,
                        contentDescription = null,
                        tint = FlameGold,
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "SRI LANKA PAYMENT OPTIONS",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Transfer to any method below, then send a screenshot of the payment receipt to WhatsApp 075 503 9897 for instant panel key delivery.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Step by Step Guide
        Text(
            text = "HOW TO GET YOUR KEY IN 3 STEPS",
            style = MaterialTheme.typography.labelLarge,
            color = FireAmber,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StepPill(step = "1", title = "Pay Amount", desc = "3,500 LKR for 31D VIP", modifier = Modifier.weight(1f))
            StepPill(step = "2", title = "Save Slip", desc = "Take screenshot / SMS", modifier = Modifier.weight(1f))
            StepPill(step = "3", title = "WhatsApp", desc = "Send to 075 503 9897", modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 1. eZ Cash & Mobile Money
        PaymentCard(
            title = "eZ Cash / mCash",
            subtitle = "Dialog & Mobitel (Fastest)",
            icon = Icons.Default.PhoneAndroid,
            accentColor = WhatsAppGreen
        ) {
            PaymentDetailRow(
                label = "eZ Cash / mCash Number",
                value = "075 503 9897",
                onCopy = { copyToClipboard("eZ Cash Number", "0755039897") }
            )
            PaymentDetailRow(
                label = "Receiver Name",
                value = "Pavin FX Official",
                onCopy = { copyToClipboard("Receiver Name", "Pavin FX Official") }
            )
            PaymentDetailRow(
                label = "Dialog / Mobitel Reload",
                value = "075 503 9897",
                onCopy = { copyToClipboard("Reload Number", "0755039897") }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 2. Commercial Bank
        PaymentCard(
            title = "Commercial Bank of Ceylon",
            subtitle = "Online Banking & CDM Deposit",
            icon = Icons.Default.AccountBalance,
            accentColor = CyberCyan
        ) {
            PaymentDetailRow(
                label = "Account Number",
                value = "8009247192",
                onCopy = { copyToClipboard("Commercial Bank Account", "8009247192") }
            )
            PaymentDetailRow(
                label = "Account Name",
                value = "S. Pavin",
                onCopy = { copyToClipboard("Account Name", "S. Pavin") }
            )
            PaymentDetailRow(
                label = "Branch",
                value = "Batticaloa City Branch",
                onCopy = { copyToClipboard("Branch", "Batticaloa") }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 3. Bank of Ceylon (BOC)
        PaymentCard(
            title = "Bank of Ceylon (BOC)",
            subtitle = "SmartPay / CDM / Transfer",
            icon = Icons.Default.AccountBalance,
            accentColor = FireAmber
        ) {
            PaymentDetailRow(
                label = "Account Number",
                value = "741829031",
                onCopy = { copyToClipboard("BOC Account", "741829031") }
            )
            PaymentDetailRow(
                label = "Account Name",
                value = "S. Pavin (Pavin FX)",
                onCopy = { copyToClipboard("Account Name", "S. Pavin") }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 4. Crypto / Binance USDT
        PaymentCard(
            title = "Binance Pay / USDT (TRC20)",
            subtitle = "Instant Worldwide Crypto",
            icon = Icons.Default.QrCode,
            accentColor = FlameGold
        ) {
            PaymentDetailRow(
                label = "Binance Pay ID",
                value = "482910482",
                onCopy = { copyToClipboard("Binance Pay ID", "482910482") }
            )
            PaymentDetailRow(
                label = "USDT (TRC-20 Address)",
                value = "TCgX9K2bNn7VzQ4wP1p8L4dE6YjRmZsT3k",
                onCopy = { copyToClipboard("USDT TRC20 Address", "TCgX9K2bNn7VzQ4wP1p8L4dE6YjRmZsT3k") }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Direct WhatsApp Contact Button
        Button(
            onClick = {
                viewModel.repository.launchWhatsApp(
                    "Hello Pavin FX! I would like to get payment verification for the 31 Day VIP Panel (3500 LKR). Please check my receipt."
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("payment_contact_whatsapp_button"),
            colors = ButtonDefaults.buttonColors(
                containerColor = WhatsAppGreen,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Send Payment Slip to WhatsApp 075 503 9897",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun StepPill(step: String, title: String, desc: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(SurfaceDark)
            .border(1.dp, CardStroke, RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(FireOrange),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = step,
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = desc,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                fontSize = 10.sp
            )
        }
    }
}

@Composable
fun PaymentCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accentColor: Color,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, CardStroke, RoundedCornerShape(14.dp)),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(accentColor.copy(alpha = 0.15f))
                        .border(1.dp, accentColor, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(DarkBg.copy(alpha = 0.6f))
                    .padding(10.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
fun PaymentDetailRow(
    label: String,
    value: String,
    onCopy: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCopy() }
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                fontSize = 11.sp
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(SurfaceElevated)
                .border(1.dp, CardStroke, RoundedCornerShape(6.dp))
                .padding(horizontal = 8.dp, vertical = 5.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = "Copy",
                    tint = CyberCyan,
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Copy",
                    color = CyberCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
