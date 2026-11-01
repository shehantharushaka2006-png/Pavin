package com.example.data.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.data.local.PavinFxDatabase
import com.example.data.model.ActivatedLicense
import com.example.data.model.OrderRecord
import com.example.data.model.PanelPlan
import kotlinx.coroutines.flow.Flow
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class PavinFxRepository(private val context: Context) {
    private val db = PavinFxDatabase.getInstance(context)
    private val orderDao = db.orderDao()
    private val licenseDao = db.licenseDao()

    companion object {
        const val ADMIN_WHATSAPP_RAW = "075 503 9897"
        const val ADMIN_WHATSAPP_INTL = "+94755039897"
        const val ADMIN_WHATSAPP_DIGITS = "94755039897"
    }

    val allOrders: Flow<List<OrderRecord>> = orderDao.getAllOrders()
    val activeLicense: Flow<ActivatedLicense?> = licenseDao.getActiveLicense()
    val allLicenses: Flow<List<ActivatedLicense>> = licenseDao.getAllLicenses()

    suspend fun saveOrder(order: OrderRecord): Long {
        return orderDao.insertOrder(order)
    }

    suspend fun deleteOrder(id: Long) {
        orderDao.deleteOrder(id)
    }

    suspend fun activateKey(inputKey: String): Result<ActivatedLicense> {
        val trimmed = inputKey.trim().uppercase()
        if (trimmed.length < 8) {
            return Result.failure(Exception("Invalid key format. License keys must be at least 8 characters."))
        }

        // Determine validity and plan duration based on key
        val (planName, days) = when {
            trimmed.contains("31D") || trimmed.contains("VIP") || trimmed.contains("9897") -> {
                "31 Day VIP Panel (3500 LKR)" to 31
            }
            trimmed.contains("7D") || trimmed.contains("LITE") -> {
                "7 Day Lite Trial (1200 LKR)" to 7
            }
            trimmed.contains("60D") || trimmed.contains("MASTER") -> {
                "60 Day Master Tier (6200 LKR)" to 60
            }
            trimmed.contains("LIFE") || trimmed.contains("ELITE") -> {
                "Lifetime Elite Panel (11500 LKR)" to 3650
            }
            else -> {
                "31 Day VIP Panel (3500 LKR)" to 31
            }
        }

        val now = System.currentTimeMillis()
        val expires = now + (days.toLong() * 24 * 60 * 60 * 1000L)
        val license = ActivatedLicense(
            licenseKey = trimmed,
            planName = planName,
            durationDays = days,
            activatedTimestamp = now,
            expiresTimestamp = expires,
            isActive = true
        )
        licenseDao.insertLicense(license)
        return Result.success(license)
    }

    fun buildWhatsAppMessage(
        plan: PanelPlan,
        gamerIgn: String,
        gamerUid: String,
        deviceModel: String,
        paymentMethod: String,
        orderRef: String
    ): String {
        val dateStr = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(Date())
        return buildString {
            appendLine("━━━━━━━━━━━━━━━━━━━━━")
            appendLine("🔥 *PAVIN FX PANEL ORDER* 🔥")
            appendLine("━━━━━━━━━━━━━━━━━━━━━")
            appendLine("📋 *Order Ref:* #$orderRef")
            appendLine("🗓️ *Date:* $dateStr")
            appendLine("")
            appendLine("👑 *Plan:* ${plan.name}")
            appendLine("⏳ *Duration:* ${plan.durationDays} Days")
            appendLine("💰 *Price:* ${plan.priceLkr} LKR")
            appendLine("")
            appendLine("🎮 *Gamer IGN:* ${if (gamerIgn.isNotBlank()) gamerIgn else "Not provided"}")
            appendLine("🆔 *UID / Account:* ${if (gamerUid.isNotBlank()) gamerUid else "Not provided"}")
            appendLine("📱 *Device:* ${if (deviceModel.isNotBlank()) deviceModel else "Android"}")
            appendLine("💳 *Payment Method:* $paymentMethod")
            appendLine("")
            appendLine("Hello Pavin FX! I would like to purchase the *${plan.name}* for *${plan.priceLkr} LKR*. Please confirm payment and send my activation key.")
            appendLine("━━━━━━━━━━━━━━━━━━━━━")
        }
    }

    fun launchWhatsApp(message: String): Boolean {
        return try {
            val encodedMessage = URLEncoder.encode(message, "UTF-8")
            val uri = Uri.parse("https://wa.me/$ADMIN_WHATSAPP_DIGITS?text=$encodedMessage")
            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun generateOrderRef(): String {
        val randomNum = (10000..99999).random()
        return "PFX-$randomNum"
    }
}
