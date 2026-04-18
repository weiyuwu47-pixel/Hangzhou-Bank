package com.example.hzbank

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.activity.compose.BackHandler
import androidx.activity.enableEdgeToEdge
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.hzbank.ui.theme.HZBankTheme
import kotlinx.coroutines.delay

class FingerprintActivity : FragmentActivity() {

    private var hasPromptShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val composeView = ComposeView(this).apply {
            layoutParams = android.view.ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            setContent {
                HZBankTheme {
                    FingerprintLockScreen(
                        onClose = { finish() },
                        onAutoStart = {
                            if (!hasPromptShown) {
                                hasPromptShown = true
                                showFingerprintPrompt()
                            }
                        }
                    )
                }
            }
        }

        setContentView(composeView)
    }

    private fun showFingerprintPrompt() {
        val biometricManager = BiometricManager.from(this)
        val canAuthenticate = biometricManager.canAuthenticate(
            BiometricManager.Authenticators.BIOMETRIC_WEAK
        )

        if (canAuthenticate != BiometricManager.BIOMETRIC_SUCCESS) {
            return
        }

        val executor = ContextCompat.getMainExecutor(this)

        val biometricPrompt = BiometricPrompt(
            this,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    startActivity(Intent(this@FingerprintActivity, MainActivity::class.java))
                    finish()
                }

                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }
            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("指纹验证")
            .setSubtitle("请按压屏内指纹感应区域")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_WEAK)
            .setNegativeButtonText("取消")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}

@Composable
fun FingerprintLockScreen(
    onClose: () -> Unit,
    onAutoStart: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(300)
        onAutoStart()
    }

    BackHandler {
        onClose()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(
            text = "请按压屏内指纹感应区域验证指纹",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp)
        )

        Text(
            text = "×",
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 28.dp)
                .clickable { onClose() }
        )
    }
}