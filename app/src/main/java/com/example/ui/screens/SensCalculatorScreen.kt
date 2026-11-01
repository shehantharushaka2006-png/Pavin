package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Radar
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
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
fun SensCalculatorScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    val sensConfig by viewModel.sensConfig.collectAsState()
    val selectedWeapon by viewModel.selectedWeaponType.collectAsState()
    val selectedDpi by viewModel.selectedDpi.collectAsState()
    val isBoosting by viewModel.isBoosting.collectAsState()
    val boostSuccess by viewModel.boostSuccess.collectAsState()

    val crosshairColor by viewModel.crosshairColor.collectAsState()
    val crosshairSize by viewModel.crosshairSize.collectAsState()
    val crosshairStyle by viewModel.crosshairStyle.collectAsState()

    val weaponOptions = listOf(
        "All Guns (Universal)",
        "Shotgun (M1887 / M1014)",
        "SMG (MP40 / UMP)",
        "AR (AK47 / SCAR / M4A1)",
        "Sniper (AWM / Kar98k)"
    )

    val dpiOptions = listOf(360, 411, 480, 600)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
            .testTag("sens_calculator_screen")
    ) {
        Spacer(modifier = Modifier.height(14.dp))

        // Title
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(CyberCyan.copy(alpha = 0.2f))
                    .border(1.dp, CyberCyan, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Tune, contentDescription = null, tint = CyberCyan, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "PRO SENSITIVITY & HUD ENGINE",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Calibrated for Free Fire headshot precision",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Weapon Selector
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, CardStroke, RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = SurfaceDark),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "SELECT WEAPON CATEGORY",
                    style = MaterialTheme.typography.labelLarge,
                    color = FireAmber,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    weaponOptions.forEach { weapon ->
                        val isSelected = selectedWeapon == weapon
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) SurfaceElevated else DarkBg)
                                .border(
                                    width = if (isSelected) 1.5.dp else 1.dp,
                                    color = if (isSelected) FireOrange else CardStroke,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { viewModel.selectWeapon(weapon) }
                                .padding(horizontal = 12.dp, vertical = 9.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = weapon,
                                    color = if (isSelected) Color.White else TextSecondary,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    fontSize = 13.sp
                                )
                                if (isSelected) {
                                    Icon(
                                        imageVector = Icons.Default.SportsEsports,
                                        contentDescription = null,
                                        tint = FireAmber,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // DPI Selector
                Text(
                    text = "SELECT DEVICE SCREEN DPI",
                    style = MaterialTheme.typography.labelLarge,
                    color = CyberCyan,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    dpiOptions.forEach { dpi ->
                        val isSelected = selectedDpi == dpi
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) FireOrange else DarkBg)
                                .border(
                                    1.dp,
                                    if (isSelected) FlameGold else CardStroke,
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable { viewModel.selectDpi(dpi) }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$dpi DPI",
                                color = if (isSelected) Color.White else TextSecondary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Calculated Sensitivity Values Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.5.dp, Brush.horizontalGradient(listOf(FireOrange, CyberCyan)), RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = SurfaceElevated),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "CALIBRATED SENSITIVITY",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "$selectedWeapon • $selectedDpi DPI",
                            style = MaterialTheme.typography.bodyMedium,
                            color = CyberCyan,
                            fontSize = 12.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(FireOrange.copy(alpha = 0.2f))
                            .border(1.dp, FireOrange, RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "FIRE BTN: ${sensConfig.fireBtnSize}%",
                            color = FlameGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sensitivity bars
                SensMeter("General Sensitivity", sensConfig.general, FireOrange)
                Spacer(modifier = Modifier.height(8.dp))
                SensMeter("Red Dot Sight", sensConfig.redDot, FireAmber)
                Spacer(modifier = Modifier.height(8.dp))
                SensMeter("2X Scope", sensConfig.scope2x, CyberCyan)
                Spacer(modifier = Modifier.height(8.dp))
                SensMeter("4X Scope", sensConfig.scope4x, CyberCyan)
                Spacer(modifier = Modifier.height(8.dp))
                SensMeter("Sniper Scope", sensConfig.sniper, FlameGold)
                Spacer(modifier = Modifier.height(8.dp))
                SensMeter("Free Look", sensConfig.freeLook, TextSecondary)

                Spacer(modifier = Modifier.height(16.dp))

                // Copy Config button
                Button(
                    onClick = {
                        val text = "Pavin FX Config ($selectedWeapon):\n" +
                                "• General: ${sensConfig.general}\n" +
                                "• Red Dot: ${sensConfig.redDot}\n" +
                                "• 2X Scope: ${sensConfig.scope2x}\n" +
                                "• 4X Scope: ${sensConfig.scope4x}\n" +
                                "• Sniper: ${sensConfig.sniper}\n" +
                                "• Free Look: ${sensConfig.freeLook}\n" +
                                "• Fire Button Size: ${sensConfig.fireBtnSize}%\n" +
                                "• DPI: ${sensConfig.dpi}\n" +
                                "Get 31-Day VIP Panel for 3500 LKR via WhatsApp 075 503 9897"
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        clipboard.setPrimaryClip(ClipData.newPlainText("Pavin FX Config", text))
                        Toast.makeText(context, "Full sensitivity profile copied to clipboard!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                        .testTag("copy_sens_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = FireOrange),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Copy Free Fire Sensitivity Profile", fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Crosshair HUD Customizer
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, CardStroke, RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = SurfaceDark),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Radar, contentDescription = null, tint = CyberCyan, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "CUSTOM CROSSHAIR HUD PREVIEW",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Canvas Target & Reticle Preview
                val activeCrosshairColor = when (crosshairColor) {
                    "Cyan" -> CyberCyan
                    "Orange" -> FireOrange
                    "Green" -> WhatsAppGreen
                    "Gold" -> FlameGold
                    else -> Color.White
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF07090F))
                        .border(1.dp, CardStroke, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    // Target Rings & Custom Crosshair Canvas
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val center = Offset(size.width / 2f, size.height / 2f)

                        // Outer and inner target rings
                        drawCircle(
                            color = Color(0xFF1E293B),
                            radius = 45.dp.toPx(),
                            center = center,
                            style = Stroke(width = 1.dp.toPx())
                        )
                        drawCircle(
                            color = Color(0xFF334155),
                            radius = 25.dp.toPx(),
                            center = center,
                            style = Stroke(width = 1.dp.toPx())
                        )

                        val sz = (crosshairSize.toFloat() / 24f) * 14.dp.toPx()

                        when (crosshairStyle) {
                            "Dot Cross" -> {
                                drawCircle(color = activeCrosshairColor, radius = 2.5.dp.toPx(), center = center)
                                drawLine(activeCrosshairColor, Offset(center.x - sz, center.y), Offset(center.x - 4.dp.toPx(), center.y), strokeWidth = 2.dp.toPx())
                                drawLine(activeCrosshairColor, Offset(center.x + 4.dp.toPx(), center.y), Offset(center.x + sz, center.y), strokeWidth = 2.dp.toPx())
                                drawLine(activeCrosshairColor, Offset(center.x, center.y - sz), Offset(center.x, center.y - 4.dp.toPx()), strokeWidth = 2.dp.toPx())
                                drawLine(activeCrosshairColor, Offset(center.x, center.y + 4.dp.toPx()), Offset(center.x, center.y + sz), strokeWidth = 2.dp.toPx())
                            }
                            "Circle Dot" -> {
                                drawCircle(color = activeCrosshairColor, radius = 2.5.dp.toPx(), center = center)
                                drawCircle(color = activeCrosshairColor, radius = sz * 0.7f, center = center, style = Stroke(width = 2.dp.toPx()))
                            }
                            "Flame Cross" -> {
                                drawCircle(color = activeCrosshairColor, radius = 3.dp.toPx(), center = center)
                                drawLine(activeCrosshairColor, Offset(center.x - sz, center.y - sz), Offset(center.x - 3.dp.toPx(), center.y - 3.dp.toPx()), strokeWidth = 2.dp.toPx())
                                drawLine(activeCrosshairColor, Offset(center.x + sz, center.y - sz), Offset(center.x + 3.dp.toPx(), center.y - 3.dp.toPx()), strokeWidth = 2.dp.toPx())
                                drawLine(activeCrosshairColor, Offset(center.x - sz, center.y + sz), Offset(center.x - 3.dp.toPx(), center.y + 3.dp.toPx()), strokeWidth = 2.dp.toPx())
                                drawLine(activeCrosshairColor, Offset(center.x + sz, center.y + sz), Offset(center.x + 3.dp.toPx(), center.y + 3.dp.toPx()), strokeWidth = 2.dp.toPx())
                            }
                        }
                    }

                    Text(
                        text = "LIVE RETICLE",
                        color = Color(0xFF475569),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Color choices
                Text("Reticle Color:", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
                Spacer(modifier = Modifier.height(6.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Cyan", "Orange", "Green", "Gold").forEach { cName ->
                        val isSelected = crosshairColor == cName
                        val pillColor = when (cName) {
                            "Cyan" -> CyberCyan
                            "Orange" -> FireOrange
                            "Green" -> WhatsAppGreen
                            "Gold" -> FlameGold
                            else -> Color.White
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) SurfaceElevated else DarkBg)
                                .border(1.5.dp, if (isSelected) pillColor else CardStroke, RoundedCornerShape(8.dp))
                                .clickable { viewModel.crosshairColor.value = cName }
                                .padding(vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = cName, color = pillColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Reticle Style Choices
                Text("Crosshair Style:", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
                Spacer(modifier = Modifier.height(6.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Dot Cross", "Circle Dot", "Flame Cross").forEach { style ->
                        val isSelected = crosshairStyle == style
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSelected) FireOrange.copy(alpha = 0.2f) else DarkBg)
                                .border(1.dp, if (isSelected) FireOrange else CardStroke, RoundedCornerShape(8.dp))
                                .clickable { viewModel.crosshairStyle.value = style }
                                .padding(vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = style,
                                color = if (isSelected) Color.White else TextSecondary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // FPS & RAM Game Booster Simulator
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, CardStroke, RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = SurfaceDark),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Speed, contentDescription = null, tint = FireOrange, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "FPS & RAM TURBO BOOSTER",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SurfaceElevated)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = if (boostSuccess) "120 FPS READY" else "STANDBY",
                            color = if (boostSuccess) WhatsAppGreen else CyberCyan,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Cleans background Android cache, frees device memory, and prepares high-refresh display buffers for zero touch lag.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MetricBox("CPU Temp", if (boostSuccess) "34°C Cool" else "41°C", FireAmber, Modifier.weight(1f))
                    MetricBox("RAM Cache", if (boostSuccess) "Cleaned (4.1GB Free)" else "2.8GB Free", CyberCyan, Modifier.weight(1f))
                    MetricBox("Refresh Rate", if (boostSuccess) "120Hz Locked" else "60Hz Standard", FlameGold, Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.runBoostOptimization() },
                    enabled = !isBoosting,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("run_boost_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (boostSuccess) WhatsAppGreen else FireOrange
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    if (isBoosting) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Optimizing GPU & RAM...")
                    } else if (boostSuccess) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("System Boosted! 120 FPS Unlocked", fontWeight = FontWeight.Bold)
                    } else {
                        Icon(Icons.Default.ElectricBolt, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Boost Game FPS & Clean RAM", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun SensMeter(label: String, value: Int, color: Color) {
    val progress = value / 100f
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium, color = TextPrimary, fontSize = 12.sp)
            Text(text = "$value", style = MaterialTheme.typography.titleMedium, color = color, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
        Spacer(modifier = Modifier.height(3.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = color,
            trackColor = DarkBg
        )
    }
}

@Composable
fun MetricBox(label: String, value: String, accent: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(DarkBg)
            .border(1.dp, CardStroke, RoundedCornerShape(8.dp))
            .padding(10.dp)
    ) {
        Column {
            Text(text = label, style = MaterialTheme.typography.bodyMedium, color = TextSecondary, fontSize = 10.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = value, style = MaterialTheme.typography.titleMedium, color = accent, fontWeight = FontWeight.Bold, fontSize = 11.sp)
        }
    }
}
