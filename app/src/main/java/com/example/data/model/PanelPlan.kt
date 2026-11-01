package com.example.data.model

data class PanelPlan(
    val id: String,
    val name: String,
    val durationDays: Int,
    val priceLkr: Int,
    val badge: String? = null,
    val features: List<String>,
    val isFeatured: Boolean = false,
    val description: String
)

val SamplePlans = listOf(
    PanelPlan(
        id = "pavin_31d_vip",
        name = "31 Day VIP Panel",
        durationDays = 31,
        priceLkr = 3500,
        badge = "🔥 MOST POPULAR • BEST VALUE",
        isFeatured = true,
        description = "The official 31-day tournament grade panel with maximum headshot sensitivity configs, 90/120 FPS optimization, and dedicated VIP WhatsApp support.",
        features = listOf(
            "Headshot Sensitivity & DPI Auto-Sync",
            "90 / 120 FPS Ultra Smooth Unlocker",
            "Pro Custom Crosshair & Dynamic Reticle",
            "Anti-Lag & RAM Touch Response Booster",
            "Instant 31-Day Activation Key",
            "Cloud Config Auto-Updates",
            "24/7 Dedicated WhatsApp Admin Support"
        )
    ),
    PanelPlan(
        id = "pavin_7d_trial",
        name = "7 Day Lite Trial",
        durationDays = 7,
        priceLkr = 1200,
        badge = "⚡ QUICK STARTER",
        isFeatured = false,
        description = "Ideal for players looking to test Pavin FX's enhanced sensitivity and FPS configurations before committing.",
        features = listOf(
            "Balanced Gun Sensitivity Profile",
            "60/90 FPS Frame Stabilizer",
            "Standard Pro Crosshair Overlays",
            "7 Days Full Panel Access",
            "WhatsApp Setup Guidance"
        )
    ),
    PanelPlan(
        id = "pavin_60d_master",
        name = "60 Day Master Tier",
        durationDays = 60,
        priceLkr = 6200,
        badge = "💎 SAVE 800 LKR",
        isFeatured = false,
        description = "Extended 2-month championship access for serious competitive squad leaders and ranked pushers.",
        features = listOf(
            "All 31-Day VIP Features Included",
            "60 Days Uninterrupted License Key",
            "Priority Cloud Config Pre-releases",
            "Advanced Shotgun & Sniper Calibration",
            "VIP WhatsApp Direct Line Priority"
        )
    ),
    PanelPlan(
        id = "pavin_lifetime_elite",
        name = "Lifetime Elite Panel",
        durationDays = 3650,
        priceLkr = 11500,
        badge = "👑 TOURNAMENT ELITE",
        isFeatured = false,
        description = "Permanent lifetime access with private configuration repository and custom 1-on-1 tuning.",
        features = listOf(
            "Lifetime Unlimited License Key",
            "Custom Sensitivity Tune for Your Phone",
            "Private VIP WhatsApp Telegram Group",
            "All Future Updates Free for Life",
            "Direct 1-on-1 Admin Setup"
        )
    )
)
