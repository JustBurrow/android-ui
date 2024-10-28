package kr.lul.android.ui.scaffold.compose.top

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kr.lul.android.ui.compose.Text
import kr.lul.android.ui.scaffold.state.top.TextTopState

/**
 * 상단바에 문자열을 표시하는 상태.
 */
@Composable
fun TextTopBar(state: TextTopState, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = state.horizontalArrangement,
        verticalAlignment = state.verticalAlignment
    ) {
        Text(state = state.text)
    }
}