package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.PanelPlan
import com.example.ui.components.PlanCard
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

@Composable
fun StoreScreen(
    viewModel: MainViewModel,
    onNavigateToPayments: () -> Unit
) {
    val plans = viewModel.plans
    val featuredPlan = viewModel.featuredPlan

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(horizontal = 16.dp)
            .testTag("store_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(6.dp))
            // Hero Banner Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .border(
                        1.5.dp,
                        Brush.linearGradient(listOf(FireOrange, CyberCyan)),
                        RoundedCornerShape(18.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_pavin_hero),
                    contentDescription = "Pavin FX Championship Banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Dark gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, Color.Black.copy(alpha = 0.85f))
                            )
                        )
                )

                // Text overlay
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(FireOrange)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "OFFICIAL FREE FIRE PANEL STORE",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "PAVIN FX VIP SUITE",
                        style = MaterialTheme.typography.displayMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Pro Headshot Sens • 120 FPS Unlocker • WhatsApp: 075 503 9897",
                        style = MaterialTheme.typography.bodyMedium,
                        color = CyberCyan
                    )
                }
            }
        }

        // Ticker / Quick Guarantee Bar
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                border = androidx.compose.foundation.BorderStroke(1.dp, Brush.horizontalGradient(listOf(FireOrange, FlameGold))),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Speed,
                            contentDescription = null,
                            tint = FireAmber,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "31-Day VIP: 3500 LKR",
                            style = MaterialTheme.typography.labelLarge,
                            color = FlameGold,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { onNavigateToPayments() }
                    ) {
                        Text(
                            text = "eZ Cash / Bank",
                            style = MaterialTheme.typography.labelMedium,
                            color = CyberCyan,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.Payment,
                            contentDescription = null,
                            tint = CyberCyan,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }

        // Featured Plan Section Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "FEATURED 31-DAY SUBSCRIPTION",
                    style = MaterialTheme.typography.titleMedium,
                    color = FireAmber,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(FireOrange.copy(alpha = 0.2f))
                        .border(1.dp, FireOrange, RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "TOP CHOICE",
                        color = FireOrange,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )
                }
            }
        }

        // Featured 31 Day VIP Panel Card
        item {
            PlanCard(
                plan = featuredPlan,
                onQuickWhatsApp = { plan -> viewModel.quickOrderWhatsApp(plan) },
                onOpenOrderDialog = { plan -> viewModel.openOrderDialog(plan) }
            )
        }

        // Visual Features Showcase Box
        item {
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
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = null,
                            tint = CyberCyan,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "WHAT'S INSIDE PAVIN FX PANEL?",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_panel_features),
                            contentDescription = "Panel Features Display",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "• Auto-Aim Gun Sensitivity calibrated for M1887, MP40, AK & AWM\n" +
                                "• 90Hz & 120Hz Ultra Smooth graphics config\n" +
                                "• Anti-Lag & RAM Touch Latency booster\n" +
                                "• Custom Esports Crosshair with dynamic fire bloom\n" +
                                "• 31 Days Continuous VIP Server license with daily auto-sync",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        lineHeight = 22.sp
                    )
                }
            }
        }

        // Section: All Other Available Plans
        item {
            Text(
                text = "MORE MEMBERSHIP TIERS",
                style = MaterialTheme.typography.titleMedium,
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }

        // Other plans
        items(plans.filter { !it.isFeatured }) { plan ->
            PlanCard(
                plan = plan,
                onQuickWhatsApp = { p -> viewModel.quickOrderWhatsApp(p) },
                onOpenOrderDialog = { p -> viewModel.openOrderDialog(p) }
            )
        }

        // Customer Reviews & Proof Gallery
        item {
            ReviewsSection()
        }

        // FAQ Section
        item {
            FaqSection()
        }

        // Bottom WhatsApp direct banner
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(SurfaceElevated, SurfaceDark)
                        )
                    )
                    .border(1.dp, WhatsAppGreen, RoundedCornerShape(14.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(WhatsAppGreen),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Questions? Chat with Pavin FX Admin",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "WhatsApp Hotline: 075 503 9897",
                                style = MaterialTheme.typography.bodyMedium,
                                color = WhatsAppGreen,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            viewModel.repository.launchWhatsApp(
                                "Hello Pavin FX! I have a question about the Free Fire 31 Day VIP Panel (3500 LKR)."
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Start WhatsApp Chat", fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ReviewsSection() {
    val reviews = listOf(
        Triple("Niroshan (Batticaloa)", "⭐⭐⭐⭐⭐", "Bought the 31-day panel for 3500 LKR. My MP40 headshot rate jumped to 78%! Key received in 4 minutes on WhatsApp."),
        Triple("Kavindu_FF (Colombo)", "⭐⭐⭐⭐⭐", "eZ cash payment was super easy. 075 503 9897 admin answered immediately and gave me the best DPI settings for my Redmi Note 11."),
        Triple("Team Lanka Esports", "⭐⭐⭐⭐⭐", "Using Pavin FX panel for CS Ranked tournament matches. Smooth 90 FPS with no frame drops.")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceDark)
            .border(1.dp, CardStroke, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Star, contentDescription = null, tint = FlameGold, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "COMMUNITY REVIEWS & PROOF",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        reviews.forEach { (author, stars, comment) ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(DarkBg)
                    .border(1.dp, CardStroke, RoundedCornerShape(10.dp))
                    .padding(12.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = author,
                                style = MaterialTheme.typography.titleMedium,
                                color = FireAmber,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(Icons.Default.Verified, contentDescription = null, tint = CyberCyan, modifier = Modifier.size(13.dp))
                        }
                        Text(text = stars, fontSize = 11.sp)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = comment,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun FaqSection() {
    var expandedIndex by remember { mutableStateOf<Int?>(0) }

    val faqs = listOf(
        "How quickly do I get my 31-day panel key?" to "Instantly! Once you transfer 3500 LKR via eZ Cash or Bank and send the slip to WhatsApp (075 503 9897), our admin generates and sends your VIP license key within 3-5 minutes.",
        "How do I pay using eZ Cash or Mobile Reload?" to "Simply transfer 3500 LKR to our official eZ Cash / Reload number: 075 503 9897. Keep the confirmation SMS screenshot and message us on WhatsApp.",
        "Does Pavin FX work on all Android devices?" to "Yes! Pavin FX is optimized for all Android devices from Android 7 to Android 15 (Xiaomi, Samsung, Realme, Infinix, Vivo, Poco, TECNO, ASUS ROG, OnePlus).",
        "How do I activate my key in this app?" to "Go to the 'Activator' tab in this app, paste the license key provided on WhatsApp, and tap 'Activate VIP Panel'. Your panel features and countdown will unlock immediately!"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceDark)
            .border(1.dp, CardStroke, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.HelpOutline, contentDescription = null, tint = CyberCyan, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "FREQUENTLY ASKED QUESTIONS",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        faqs.forEachIndexed { index, (q, a) ->
            val isExpanded = expandedIndex == index
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(SurfaceElevated)
                    .clickable { expandedIndex = if (isExpanded) null else index }
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = q,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null,
                        tint = FireAmber
                    )
                }

                AnimatedVisibility(visible = isExpanded) {
                    Column {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = a,
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary,
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                }
            }
        }
    }
}
