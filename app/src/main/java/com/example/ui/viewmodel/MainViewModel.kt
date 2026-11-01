package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.ActivatedLicense
import com.example.data.model.OrderRecord
import com.example.data.model.PanelPlan
import com.example.data.model.SamplePlans
import com.example.data.repository.PavinFxRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppNavTab(val title: String) {
    STORE("VIP Store"),
    ACTIVATOR("Activator"),
    SENSITIVITY("Sens Tool"),
    ORDERS("My Orders"),
    PAYMENT("Payments")
}

data class SensConfig(
    val general: Int,
    val redDot: Int,
    val scope2x: Int,
    val scope4x: Int,
    val sniper: Int,
    val freeLook: Int,
    val fireBtnSize: Int,
    val dpi: Int
)

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val repository = PavinFxRepository(application)

    val plans: List<PanelPlan> = SamplePlans
    val featuredPlan: PanelPlan = SamplePlans.first { it.id == "pavin_31d_vip" }

    // Navigation
    private val _currentTab = MutableStateFlow(AppNavTab.STORE)
    val currentTab: StateFlow<AppNavTab> = _currentTab.asStateFlow()

    // Dialogs
    private val _orderPlan = MutableStateFlow<PanelPlan?>(null)
    val orderPlan: StateFlow<PanelPlan?> = _orderPlan.asStateFlow()

    private val _showPaymentSheet = MutableStateFlow(false)
    val showPaymentSheet: StateFlow<Boolean> = _showPaymentSheet.asStateFlow()

    // Order form inputs
    val gamerIgn = MutableStateFlow("")
    val gamerUid = MutableStateFlow("")
    val deviceModel = MutableStateFlow("")
    val selectedPaymentMethod = MutableStateFlow("Bank Transfer (Commercial / BOC / Sampath)")

    // Key activation
    val keyInput = MutableStateFlow("")
    private val _activationMessage = MutableStateFlow<String?>(null)
    val activationMessage: StateFlow<String?> = _activationMessage.asStateFlow()

    private val _isActivating = MutableStateFlow(false)
    val isActivating: StateFlow<Boolean> = _isActivating.asStateFlow()

    // Sensitivity Tool State
    private val _selectedWeaponType = MutableStateFlow("All Guns (Universal)")
    val selectedWeaponType: StateFlow<String> = _selectedWeaponType.asStateFlow()

    private val _selectedDpi = MutableStateFlow(411)
    val selectedDpi: StateFlow<Int> = _selectedDpi.asStateFlow()

    private val _sensConfig = MutableStateFlow(calculateSens("All Guns (Universal)", 411))
    val sensConfig: StateFlow<SensConfig> = _sensConfig.asStateFlow()

    // FPS / Booster simulator
    private val _isBoosting = MutableStateFlow(false)
    val isBoosting: StateFlow<Boolean> = _isBoosting.asStateFlow()

    private val _boostSuccess = MutableStateFlow(false)
    val boostSuccess: StateFlow<Boolean> = _boostSuccess.asStateFlow()

    // Crosshair customizer
    val crosshairColor = MutableStateFlow("Cyan")
    val crosshairSize = MutableStateFlow(24)
    val crosshairStyle = MutableStateFlow("Dot Cross")

    // Database Flows
    val allOrders: StateFlow<List<OrderRecord>> = repository.allOrders
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeLicense: StateFlow<ActivatedLicense?> = repository.activeLicense
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun setTab(tab: AppNavTab) {
        _currentTab.value = tab
    }

    fun openOrderDialog(plan: PanelPlan) {
        _orderPlan.value = plan
    }

    fun dismissOrderDialog() {
        _orderPlan.value = null
    }

    fun togglePaymentSheet(show: Boolean) {
        _showPaymentSheet.value = show
    }

    fun submitOrder(plan: PanelPlan, onComplete: (String) -> Unit) {
        val orderRef = repository.generateOrderRef()
        val order = OrderRecord(
            orderRef = orderRef,
            planName = plan.name,
            durationDays = plan.durationDays,
            priceLkr = plan.priceLkr,
            gamerIgn = gamerIgn.value.trim(),
            gamerUid = gamerUid.value.trim(),
            deviceModel = deviceModel.value.trim(),
            paymentMethod = selectedPaymentMethod.value
        )

        viewModelScope.launch {
            repository.saveOrder(order)
            val msg = repository.buildWhatsAppMessage(
                plan = plan,
                gamerIgn = gamerIgn.value.trim(),
                gamerUid = gamerUid.value.trim(),
                deviceModel = deviceModel.value.trim(),
                paymentMethod = selectedPaymentMethod.value,
                orderRef = orderRef
            )
            repository.launchWhatsApp(msg)
            dismissOrderDialog()
            onComplete(msg)
        }
    }

    fun quickOrderWhatsApp(plan: PanelPlan) {
        val orderRef = repository.generateOrderRef()
        val msg = repository.buildWhatsAppMessage(
            plan = plan,
            gamerIgn = "",
            gamerUid = "",
            deviceModel = "",
            paymentMethod = "WhatsApp Direct Inquiry",
            orderRef = orderRef
        )
        viewModelScope.launch {
            repository.saveOrder(
                OrderRecord(
                    orderRef = orderRef,
                    planName = plan.name,
                    durationDays = plan.durationDays,
                    priceLkr = plan.priceLkr,
                    gamerIgn = "Fast Checkout",
                    gamerUid = "",
                    deviceModel = "Android",
                    paymentMethod = "WhatsApp Direct"
                )
            )
            repository.launchWhatsApp(msg)
        }
    }

    fun activateKey() {
        val key = keyInput.value.trim()
        if (key.isBlank()) {
            _activationMessage.value = "⚠️ Please enter your Pavin FX license key"
            return
        }

        viewModelScope.launch {
            _isActivating.value = true
            delay(800) // Brief validation visual feedback
            val result = repository.activateKey(key)
            _isActivating.value = false
            if (result.isSuccess) {
                val lic = result.getOrThrow()
                _activationMessage.value = "✅ Activated successfully! ${lic.planName} is active for ${lic.durationDays} days."
                keyInput.value = ""
            } else {
                _activationMessage.value = "❌ ${result.exceptionOrNull()?.message ?: "Invalid key"}"
            }
        }
    }

    fun deleteOrder(id: Long) {
        viewModelScope.launch {
            repository.deleteOrder(id)
        }
    }

    fun selectWeapon(type: String) {
        _selectedWeaponType.value = type
        _sensConfig.value = calculateSens(type, _selectedDpi.value)
    }

    fun selectDpi(dpi: Int) {
        _selectedDpi.value = dpi
        _sensConfig.value = calculateSens(_selectedWeaponType.value, dpi)
    }

    fun runBoostOptimization() {
        viewModelScope.launch {
            _isBoosting.value = true
            _boostSuccess.value = false
            delay(1500)
            _isBoosting.value = false
            _boostSuccess.value = true
        }
    }

    private fun calculateSens(weapon: String, dpi: Int): SensConfig {
        return when (weapon) {
            "Shotgun (M1887 / M1014)" -> {
                val base = if (dpi >= 480) 98 else 100
                SensConfig(
                    general = base,
                    redDot = 92,
                    scope2x = 88,
                    scope4x = 84,
                    sniper = 60,
                    freeLook = 80,
                    fireBtnSize = 45,
                    dpi = dpi
                )
            }
            "SMG (MP40 / UMP)" -> {
                SensConfig(
                    general = 99,
                    redDot = 95,
                    scope2x = 92,
                    scope4x = 86,
                    sniper = 55,
                    freeLook = 85,
                    fireBtnSize = 48,
                    dpi = dpi
                )
            }
            "AR (AK47 / SCAR / M4A1)" -> {
                SensConfig(
                    general = 96,
                    redDot = 90,
                    scope2x = 87,
                    scope4x = 82,
                    sniper = 50,
                    freeLook = 75,
                    fireBtnSize = 52,
                    dpi = dpi
                )
            }
            "Sniper (AWM / Kar98k)" -> {
                SensConfig(
                    general = 92,
                    redDot = 85,
                    scope2x = 80,
                    scope4x = 76,
                    sniper = 42,
                    freeLook = 70,
                    fireBtnSize = 55,
                    dpi = dpi
                )
            }
            else -> {
                SensConfig(
                    general = 98,
                    redDot = 93,
                    scope2x = 90,
                    scope4x = 85,
                    sniper = 52,
                    freeLook = 80,
                    fireBtnSize = 49,
                    dpi = dpi
                )
            }
        }
    }
}
