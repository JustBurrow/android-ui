package kr.lul.android.ui.scaffold.compose.snackbar

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import kr.lul.android.ui.scaffold.state.MessageSnackbarState

@Composable
fun MessageSnackbar(state: MessageSnackbarState, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val hostState = remember { SnackbarHostState() }

    LaunchedEffect(state.key) {
        scope.launch {
            hostState.showSnackbar(state.message, state.actionLabel, state.withDismissAction, state.duration)
        }
    }

    SnackbarHost(
        hostState = hostState,
        modifier = modifier
    )
}