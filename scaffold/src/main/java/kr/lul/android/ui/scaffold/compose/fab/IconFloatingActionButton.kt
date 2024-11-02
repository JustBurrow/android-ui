package kr.lul.android.ui.scaffold.compose.fab

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import kr.lul.android.ui.compose.Icon
import kr.lul.android.ui.scaffold.compose.TAG
import kr.lul.android.ui.scaffold.state.fab.IconFabState
import kr.lul.android.ui.scaffold.state.fab.IconFabStateProvider

/**
 * [androidx.compose.material3.IconButton]을 하나 표시하는 FAB의 상태.
 */
@Composable
fun IconFloatingActionButton(state: IconFabState, modifier: Modifier = Modifier) {
    Log.v(TAG, "#FloatingActionButton state=$state, modifier=$modifier")

    androidx.compose.material3.FloatingActionButton(onClick = state.onClick, modifier = modifier) {
        Icon(state.icon)
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewIconFloatingActionButton(@PreviewParameter(IconFabStateProvider::class) state: IconFabState) {
    MaterialTheme {
        IconFloatingActionButton(state)
    }
}