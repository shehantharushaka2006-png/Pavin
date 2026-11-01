package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.SamplePlans
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Pavin FX", appName)
    }

    @Test
    fun `verify featured 31 day plan details`() {
        val vipPlan = SamplePlans.find { it.id == "pavin_31d_vip" }
        assertNotNull(vipPlan)
        assertEquals(31, vipPlan!!.durationDays)
        assertEquals(3500, vipPlan.priceLkr)
        assertTrue(vipPlan.isFeatured)
    }
}
