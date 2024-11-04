package kr.lul.android.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kr.lul.android.ui.state.IconState
import kr.lul.android.ui.state.IconStateProvider
import kr.lul.android.ui.state.State
import kr.lul.android.ui.state.TextState
import kr.lul.android.ui.state.TextStateProvider

/**
 * [State]를 기반 UI 딜리게이터.
 *
 * 지원하는 UI :
 * - [kr.lul.android.ui.compose.Icon]
 * - [kr.lul.android.ui.compose.Text]
 *
 * @param state UI 상태.
 * @p aram modifier [Modifier].
 */
@Composable
fun StateComponent(state: State, modifier: Modifier = Modifier) {
    when (state) {
        is IconState -> Icon(state, modifier)
        is TextState -> Text(state, modifier)
        else -> throw IllegalArgumentException("Unsupported state : state::class = ${state::class}, state = $state")
    }
}

private class StateProvider : PreviewParameterProvider<State> {
    override val values: Sequence<State> = IconStateProvider().values + TextStateProvider().values
}

@Composable
@Preview
private fun PreviewStateComponent(@PreviewParameter(StateProvider::class) state: State) {
    StateComponent(state)
}