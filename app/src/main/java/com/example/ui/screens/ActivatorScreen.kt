package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import com.example.ui.viewmodel.AppNavTab
import com.example.ui.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ActivatorScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    val keyInput by viewModel.keyInput.collectAsState()
    val isActivating by viewModel.isActivating.collectAsState()
    val activationMessage by viewModel.activationMessage.collectAsState()
    val activeLicense by viewModel.activeLicense.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
            .testTag("activator_screen")
    ) {
        Spacer(modifier = Modifier.height(14.dp))

        // Screen Header
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(FireOrange.copy(alpha = 0.2f))
                    .border(1.dp, FireOrange, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.VpnKey, contentDescription = null, tint = FireOrange, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "PAVIN FX LICENSE ACTIVATOR",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Activate your 31-day VIP panel or verify status",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Active Status Card (if user has active license)
        if (activeLicense != null) {
            val lic = activeLicense!!
            val daysLeft = maxOf(0, ((lic.expiresTimestamp - System.currentTimeMillis()) / (1000 * 60 * 60 * 24)).toInt())
            val dateFmt = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        2.dp,
                        Brush.linearGradient(listOf(FlameGold, FireOrange, CyberCyan)),
                        RoundedCornerShape(18.dp)
                    ),
                colors = CardDefaults.cardColors(containerColor = SurfaceElevated),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(WhatsAppGreen)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "VIP PANEL ACTIVE",
                                color = FlameGold,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(FireOrange)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "$daysLeft Days Left",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = lic.planName,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Key: ${lic.licenseKey}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = CyberCyan
                    )
                    Text(
                        text = "Expires: ${dateFmt.format(Date(lic.expiresTimestamp))}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Unlocked Features list
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(DarkBg.copy(alpha = 0.7f))
                            .padding(12.dp)
                    ) {
                        FeatureStatusRow("VIP Headshot Sensitivity Engine", "ACTIVE 98%")
                        FeatureStatusRow("120 FPS Ultra Frame Unlocker", "SYNCED")
                        FeatureStatusRow("Esports Custom Crosshair Reticle", "READY")
                        FeatureStatusRow("Anti-Lag Touch Latency Buffer", "OPTIMAL")
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = { viewModel.setTab(AppNavTab.SENSITIVITY) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = CyberCyan, contentColor = Color.Black),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(Icons.Default.Tune, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Configure Sens & Crosshair Now", fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Key Input Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, CardStroke, RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = SurfaceDark),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            ) {
                Text(
                    text = "ENTER ACTIVATION KEY",
                    style = MaterialTheme.typography.titleMedium,
                    color = FireAmber,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Paste the secret VIP license key sent by Pavin FX on WhatsApp.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = keyInput,
                    onValueChange = { viewModel.keyInput.value = it },
                    placeholder = { Text("e.g. PAVIN-31D-VIP-9897", color = TextMuted) },
                    singleLine = true,
                    leadingIcon = {
                        Icon(Icons.Default.Key, contentDescription = null, tint = FlameGold)
                    },
                    trailingIcon = {
                        OutlinedButton(
                            onClick = {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = clipboard.primaryClip?.getItemAt(0)?.text?.toString()
                                if (!clip.isNullOrBlank()) {
                                    viewModel.keyInput.value = clip
                                } else {
                                    Toast.makeText(context, "Clipboard empty", Toast.LENGTH_SHORT).show()
                                }
                            },
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                            modifier = Modifier.padding(end = 6.dp)
                        ) {
                            Icon(Icons.Default.ContentPaste, contentDescription = "Paste", modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Paste", fontSize = 11.sp)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("license_key_input"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = FireOrange,
                        unfocusedBorderColor = CardStroke,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Demo Key Tester button for convenience
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Need a demo trial key?",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextMuted,
                        fontSize = 12.sp
                    )
                    OutlinedButton(
                        onClick = { viewModel.keyInput.value = "PAVIN-31D-VIP-9897" },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Insert Demo 31D Key", fontSize = 11.sp, color = CyberCyan)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Activate Button
                Button(
                    onClick = { viewModel.activateKey() },
                    enabled = !isActivating,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("activate_key_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = FireOrange,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    if (isActivating) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Verifying Key...")
                    } else {
                        Icon(Icons.Default.RocketLaunch, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Activate VIP Panel", fontWeight = FontWeight.Bold)
                    }
                }

                if (activationMessage != null) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceElevated)
                            .padding(10.dp)
                    ) {
                        Text(
                            text = activationMessage!!,
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (activationMessage!!.startsWith("✅")) WhatsAppGreen else FireAmber,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Help / Don't have a key box
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
                Text(
                    text = "DON'T HAVE A KEY YET?",
                    style = MaterialTheme.typography.titleMedium,
                    color = FlameGold,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Get the 31-day VIP panel for 3,500 LKR directly from the official developer on WhatsApp (075 503 9897). Instant delivery via eZ Cash, Bank Transfer, or Reload.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        viewModel.repository.launchWhatsApp(
                            "Hello Pavin FX! I would like to buy a 31 Day VIP Panel License Key for 3500 LKR."
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Buy Key on WhatsApp (075 503 9897)", fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun FeatureStatusRow(feature: String, status: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Done, contentDescription = null, tint = WhatsAppGreen, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = feature,
                style = MaterialTheme.typography.bodyMedium,
                color = TextPrimary,
                fontSize = 12.sp
            )
        }
        Text(
            text = status,
            style = MaterialTheme.typography.labelMedium,
            color = CyberCyan,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp
        )
    }
}
