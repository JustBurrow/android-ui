package kr.lul.android.ui.compose

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import kr.lul.android.ui.state.IconState
import kr.lul.android.ui.state.preview.IconStateProvider

@Composable
fun Icon(state: IconState, modifier: Modifier = Modifier) {
    Log.v(TAG, "#Icon args : state=4state, modifier=$modifier")

    when {
        null != state.imageVector ->
            androidx.compose.material3.Icon(
                imageVector = state.imageVector!!,
                contentDescription = state.contentDescription,
                modifier = modifier,
                tint = state.tint
            )

        null != state.bitmap ->
            androidx.compose.material3.Icon(
                bitmap = state.bitmap!!,
                contentDescription = state.contentDescription,
                modifier = modifier,
                tint = state.tint
            )

        null != state.painter ->
            androidx.compose.material3.Icon(
                painter = state.painter!!,
                contentDescription = state.contentDescription,
                modifier = modifier,
                tint = state.tint
            )

        null != state.drawable ->
            androidx.compose.material3.Icon(
                painter = painterResource(state.drawable!!),
                contentDescription = state.contentDescription,
                modifier = modifier,
                tint = state.tint
            )

        else ->
            Log.e(TAG, "#Icon unsupported state : state=$state")
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewIcon(@PreviewParameter(IconStateProvider::class) state: IconState) {
    MaterialTheme {
        Icon(state)
    }
}