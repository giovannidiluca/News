package com.giovannidiluca.news

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController

@Composable
fun AuthenticationScreen(navController: NavController) {
    val context = LocalContext.current
    val biometricManager = remember { BiometricManager.from(context) }

    val isBiometricAvailable = remember {
        biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
    }
    if (isBiometricAvailable == BiometricManager.BIOMETRIC_SUCCESS)
        BiometricPrompt(
            context = context,
            onAuthenticationSuccess = navController::navigateToList,
            onAuthenticationError = { showToastError(context) }
        )
    else navController.navigateToList()
}

@Composable
fun BiometricPrompt(
    context: Context,
    onAuthenticationSuccess: () -> Unit,
    onAuthenticationError: () -> Unit
) {
    val executor = remember { ContextCompat.getMainExecutor(context) }
    val biometricPrompt = BiometricPrompt(
        context as FragmentActivity,
        executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                onAuthenticationError()
            }

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                onAuthenticationSuccess()
            }
        }
    )

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_WEAK)
        .setTitle("Biometric Authentication")
        .setSubtitle("Log in using your biometric credential")
        .setNegativeButtonText("Cancel")
        .build()

    biometricPrompt.authenticate(promptInfo)
}

fun NavController.navigateToList() {
    navigate("news_list_route") {
        launchSingleTop = true
    }
}

fun showToastError(context: Context) = Toast.makeText(
    context,
    R.string.error,
    Toast.LENGTH_LONG
).show()