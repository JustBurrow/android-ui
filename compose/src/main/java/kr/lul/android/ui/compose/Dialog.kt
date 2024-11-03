package kr.lul.android.ui.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import kr.lul.android.ui.state.DialogActionHandler
import kr.lul.android.ui.state.DialogActions
import kr.lul.android.ui.state.DialogState
import kr.lul.android.ui.state.DialogStateProvider
import kr.lul.android.ui.state.State
import kr.lul.android.ui.state.TextState

/**
 * Material3 다이얼로그 확장.
 *
 * @param state 다이얼로그 상태.
 * @param actions 다이얼로그 조작 콜백.
 * @param content 다이얼로그 내용.
 *
 * @see androidx.compose.ui.window.Dialog
 */
@Composable
fun <S : State> Dialog(
    state: DialogState<S>,
    actions: DialogActions,
    content: @Composable (S) -> Unit
) {
    Log.v(TAG, "#Dialog args : state=$state, actions=$actions")

    if (state.show) {
        androidx.compose.ui.window.Dialog(
            onDismissRequest = actions.onDismissRequest,
            properties = state.properties
        ) {
            content(state.content)
        }
    }
}

/**
 * Material3 다이얼로그 확장.
 *
 * @param state 다이얼로그 상태.
 * @param handler 다이얼로그 조작 콜백.
 * @param content 다이얼로그 내용.
 *
 * @see androidx.compose.ui.window.Dialog
 */
@Composable
fun <S : State> Dialog(
    state: DialogState<S>,
    handler: DialogActionHandler,
    content: @Composable (S) -> Unit
) {
    Dialog(
        state = state,
        actions = remember {
            object : DialogActions {
                override val onDismissRequest: () -> Unit = handler::onDismissRequest
            }
        },
        content = content
    )
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun PreviewDialog(@PreviewParameter(DialogStateProvider::class) state: DialogState<State>) {
    MaterialTheme {
        Dialog(
            state = state,
            actions = object : DialogActions {
                override val onDismissRequest: () -> Unit = {}
            }
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background, RoundedCornerShape(32.dp))
                        .padding(32.dp)
                ) {
                    Text(state.content as TextState)
                }
            }
        }
    }
}