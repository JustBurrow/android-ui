package kr.lul.android.ui.scaffold.compose.top

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import kr.lul.android.ui.compose.Text
import kr.lul.android.ui.scaffold.compose.TAG
import kr.lul.android.ui.scaffold.state.top.TextTopState
import kr.lul.android.ui.scaffold.state.top.TextTopStateProvider

/**
 * 상단바에 문자열을 표시하는 상태.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextTopBar(state: TextTopState, modifier: Modifier = Modifier) {
    Log.v(TAG, "#TextTopBar args : state=$state")

    TopAppBar(
        title = { Text(state = state.text, modifier = Modifier.fillMaxWidth()) },
        modifier = modifier
    )
}

@Composable
@Preview(showBackground = true)
private fun PreviewTextTopBar(@PreviewParameter(TextTopStateProvider::class) state: TextTopState) {
    MaterialTheme {
        TextTopBar(state = state)
    }
}