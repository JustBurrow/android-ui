package kr.lul.android.ui.scaffold.compose.dev

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import kr.lul.android.ui.scaffold.BuildConfig
import kr.lul.android.ui.scaffold.state.dev.ClickableDevState
import kr.lul.android.ui.scaffold.state.dev.DevState
import kr.lul.android.ui.scaffold.state.dev.DevStateProvider

/**
 * 개발자 도구.
 */
@Composable
fun Dev(
    state: DevState,
    modifier: Modifier = Modifier
) {
    if (BuildConfig.DEBUG && state.show) {
        when (state) {
            is ClickableDevState ->
                ClickableDev(state, modifier)

            else -> throw IllegalArgumentException(
                "Unsupported DevState type : state::class=${state::class}, state=$state"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewDev(@PreviewParameter(DevStateProvider::class) state: DevState) {
    MaterialTheme {
        Dev(state)
    }
}