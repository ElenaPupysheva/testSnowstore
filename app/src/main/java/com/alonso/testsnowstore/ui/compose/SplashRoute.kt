package com.alonso.testsnowstore.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alonso.testsnowstore.presentation.splash.SplashState
import com.alonso.testsnowstore.presentation.splash.SplashViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashRoute(
    onFinished: () -> Unit,
    vm: SplashViewModel = koinViewModel()
) {
    val state = vm.state.collectAsStateWithLifecycle().value

    LaunchedEffect(state) {
        when (state) {
            SplashState.Ready -> onFinished()
            is SplashState.Error -> onFinished()
            SplashState.Loading -> Unit
        }
    }

    SplashScreen()
}