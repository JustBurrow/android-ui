package kr.lul.android.ui.compose

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import kr.lul.android.ui.state.TextState
import kr.lul.android.ui.state.hasTestTag
import kr.lul.android.ui.state.preview.TextStateProvider

/**
 * [TextState]를 기반으로 한 [androidx.compose.material3.Text] 확장.
 *
 * @see androidx.compose.material3.Text
 */
@Composable
fun Text(
    state: TextState,
    modifier: Modifier = Modifier,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    androidx.compose.material3.Text(
        text = state.text,
        modifier = if (modifier.hasTestTag()) {
            Log.w(TAG, "#Text testTag already exists in the modifier : modifier=$modifier")
            modifier
        } else {
            modifier.testTag(state.testTag)
        },
        color = state.color,
        fontSize = state.fontSize,
        fontStyle = state.fontStyle,
        fontWeight = state.fontWeight,
        fontFamily = state.fontFamily,
        letterSpacing = state.letterSpacing,
        textDecoration = state.textDecoration,
        textAlign = state.textAlign,
        lineHeight = state.lineHeight,
        overflow = state.overflow,
        softWrap = state.softWrap,
        maxLines = state.textLines.max,
        minLines = state.textLines.min,
        onTextLayout = onTextLayout,
        inlineContent = state.inlineContent,
        style = state.style
    )
}

@Composable
@Preview(showBackground = true)
private fun PreviewText(@PreviewParameter(TextStateProvider::class) state: TextState) {
    MaterialTheme {
        Text(state)
    }
}