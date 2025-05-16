package com.example.skincareroutineplanner

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.skincareroutineplanner.data.KtorClient
import io.ktor.client.request.get
import kotlinx.coroutines.launch

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.skincareroutineplanner", appContext.packageName)
    }

    @Test
    fun test0() {
        val ip = "172.20.10.8"
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val activity = appContext as? ComponentActivity
        activity?.lifecycleScope?.launch {
            val status = KtorClient.client.get("http://$ip:8080/api/product/1").status
            if (status.value == 200) {
                Toast.makeText(appContext, "Успешно", Toast.LENGTH_SHORT).show()
            }

        }
    }
}