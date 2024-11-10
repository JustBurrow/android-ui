package kr.lul.android.ui.scaffold.compose.bottom

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import kr.lul.android.ui.navigation.compose.rememberBaseNavigator
import kr.lul.android.ui.navigation.navigator.BaseNavigator
import kr.lul.android.ui.scaffold.compose.TAG
import kr.lul.android.ui.scaffold.state.bottom.BottomState
import kr.lul.android.ui.scaffold.state.bottom.BottomStateProvider
import kr.lul.android.ui.scaffold.state.bottom.NavigationBottomState
import kr.lul.android.ui.scaffold.state.bottom.TextBottomState

/**
 * [androidx.compose.material3.Scaffold]의 하단 바.
 *
 * [Bottom app bar](https://m3.material.io/components/bottom-app-bar/overview)의 상태 홀더.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BottomBar(baseNavigator: BaseNavigator, state: BottomState, modifier: Modifier = Modifier) {
    Log.v(TAG, "#BottomBar args : baseNavigator=$baseNavigator, state=$state")

    when (state) {
        BottomState.NONE -> {}

        is TextBottomState ->
            TextBottomBar(state, modifier)

        is NavigationBottomState ->
            NavigationBottomBar(baseNavigator, state, modifier)

        else ->
            throw IllegalArgumentException("Unsupported BottomState : state::class=${state::class}, state=$state")
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewBottomBar(@PreviewParameter(BottomStateProvider::class) state: BottomState) {
    MaterialTheme {
        BottomBar(baseNavigator = rememberBaseNavigator(), state = state, modifier = Modifier.fillMaxWidth())
    }
}