package kr.lul.android.ui.scaffold.compose.fab

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import kr.lul.android.ui.scaffold.state.fab.FabState
import kr.lul.android.ui.scaffold.state.fab.FabStateProvider
import kr.lul.android.ui.scaffold.state.fab.IconFabState

@Composable
fun FloatingActionButton(state: FabState, modifier: Modifier = Modifier) {
    androidx.compose.material3.FloatingActionButton(state.onClick) { }
    when (state) {
        FabState.NONE -> {}

        is IconFabState ->
            IconFloatingActionButton(state, modifier)

        else ->
            throw IllegalArgumentException("Unsupported FabState: $state")

    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewFloatingActionButton(@PreviewParameter(FabStateProvider::class) state: FabState) {
    MaterialTheme {
        FloatingActionButton(state)
    }
}