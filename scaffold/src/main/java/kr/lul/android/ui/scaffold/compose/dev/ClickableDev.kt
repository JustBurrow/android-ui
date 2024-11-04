package kr.lul.android.ui.scaffold.compose.dev

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import kr.lul.android.ui.compose.Icon
import kr.lul.android.ui.scaffold.BuildConfig
import kr.lul.android.ui.scaffold.compose.TAG
import kr.lul.android.ui.scaffold.state.dev.ClickableDevState
import kr.lul.android.ui.scaffold.state.dev.ClickableDevStateProvider
import kr.lul.android.ui.state.hasTestTag

/**
 * 클릭할 수 있는 요소가 1개인 개발자 도구.
 *
 * @param state 클릭할 수 있는 요소를 포함한 상태.
 */
@Composable
fun ClickableDev(state: ClickableDevState, modifier: Modifier = Modifier) {
    Log.v(TAG, "#ClickableDev args : state=$state,  modifier=$modifier")

    if (BuildConfig.DEBUG && state.show) {
        val actualModifier = if (modifier.hasTestTag()) {
            Log.d(TAG, "#ClickableDev : modifier already has test tag : modifier=$modifier")
            modifier
        } else {
            modifier.testTag(state.testTag)
        }
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5F), RoundedCornerShape(8.dp))
            .padding(4.dp)

        Box(modifier = actualModifier) {
            /**
             * TODO 쓸 수 있는 버튼 종류 추가.
             */
            IconButton(onClick = state.onClick) {
                Icon(state.icon)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ClickableDevPreview(@PreviewParameter(ClickableDevStateProvider::class) state: ClickableDevState) {
    MaterialTheme {
        ClickableDev(state = state)
    }
}