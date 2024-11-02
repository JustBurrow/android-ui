package kr.lul.android.ui.scaffold.compose.bottom

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import kr.lul.android.ui.compose.Text
import kr.lul.android.ui.scaffold.compose.TAG
import kr.lul.android.ui.scaffold.state.bottom.TextBottomState
import kr.lul.android.ui.scaffold.state.bottom.TextBottomStateProvider

/**
 * 단순하게 문자열을 표시하는 하단바.
 */
@Composable
fun TextBottomBar(state: TextBottomState, modifier: Modifier = Modifier) {
    Log.v(TAG, "#TextBottomBar args : state=$state, modifier=$modifier")

    BottomAppBar(modifier = modifier) {
        Text(state = state.text, Modifier.fillMaxWidth())
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewTextBottomBar(@PreviewParameter(TextBottomStateProvider::class) state: TextBottomState) {
    MaterialTheme {
        TextBottomBar(state)
    }
}