package kr.lul.android.ui.scaffold.compose.top

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import kr.lul.android.ui.scaffold.compose.TAG
import kr.lul.android.ui.scaffold.state.top.IconTopState
import kr.lul.android.ui.scaffold.state.top.TextTopState
import kr.lul.android.ui.scaffold.state.top.TopBarStateProvider
import kr.lul.android.ui.scaffold.state.top.TopState

/**
 * 상태 홀더의 종류에 따라 해당 UI를 그리는 델리게이터.
 *
 * @param state 상태 홀더.
 * @param modifier
 *
 * @see TopState
 */
@Composable
fun TopBar(state: TopState, modifier: Modifier = Modifier) {
    Log.v(TAG, "#TopBar args : state=$state")

    when (state) {
        TopState.NONE -> {}

        is TextTopState ->
            TextTopBar(state, modifier)

        is IconTopState ->
            IconTopBar(state, modifier)

        else ->
            throw IllegalArgumentException("Unsupported TopState : state::class=${state::class}, state=$state")
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewTopBar(@PreviewParameter(TopBarStateProvider::class) state: TopState) {
    MaterialTheme {
        TopBar(state = state, modifier = Modifier.fillMaxWidth())
    }
}