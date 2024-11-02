package kr.lul.android.ui.scaffold.compose.top

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kr.lul.android.ui.compose.Icon
import kr.lul.android.ui.scaffold.state.top.IconTopState

@Composable
fun IconTopBar(state: IconTopState, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = state.horizontalArrangement,
        verticalAlignment = state.verticalAlignment
    ) {
        Icon(state.icon)
    }
}