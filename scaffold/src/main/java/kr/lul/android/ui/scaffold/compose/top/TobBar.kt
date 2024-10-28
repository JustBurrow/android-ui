package kr.lul.android.ui.scaffold.compose.top

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kr.lul.android.ui.scaffold.compose.TAG
import kr.lul.android.ui.scaffold.state.top.IconTopState
import kr.lul.android.ui.scaffold.state.top.TextTopState
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
        TopState.NONE -> {
        }

        is TextTopState ->
            TextTopBar(state, modifier)

        is IconTopState ->
            IconTopBar(state, modifier)

        else ->
            throw IllegalArgumentException("Unsupported TopState : state::class=${state::class}, state=$state")
    }
}