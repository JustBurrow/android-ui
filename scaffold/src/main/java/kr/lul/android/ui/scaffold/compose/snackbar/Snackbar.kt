package kr.lul.android.ui.scaffold.compose.snackbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kr.lul.android.ui.scaffold.state.MessageSnackbarState
import kr.lul.android.ui.scaffold.state.SnackbarState

@Composable
fun Snackbar(state: SnackbarState, modifier: Modifier = Modifier) {
    when (state) {
        SnackbarState.NONE -> {/* do nothing */
        }

        is MessageSnackbarState -> MessageSnackbar(state, modifier)

        else -> throw IllegalArgumentException("Unknown SnackbarState : state::class=${state::class}, state=$state")
    }
}