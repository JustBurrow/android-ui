package kr.lul.android.ui.compose

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import kr.lul.android.ui.state.ButtonState
import kr.lul.android.ui.state.ButtonStateProvider
import kr.lul.android.ui.state.TextState
import kr.lul.android.ui.state.hasTestTag

/**
 * Material3 버튼을 확장한 버튼 UI.
 *
 * @param state 버튼 상태.
 * @param modifier [Modifier].
 * @param shape [Shape].
 * @param colors [ButtonColors].
 * @param elevation [ButtonElevation].
 * @param border [BorderStroke].
 * @param contentPadding [PaddingValues].
 * @param interactionSource [MutableInteractionSource].
 * @param content 버튼 내용. `state.content`가 `null`이 아닌 경우 무시됨.
 *
 * @see kr.lul.android.ui.compose.StateComponent
 */
@Composable
fun Button(
    state: ButtonState,
    modifier: Modifier = Modifier,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable (() -> Unit)? = null
) {
    Log.v(
        TAG,
        listOf(
            "state=$state",
            "modifier=$modifier",
            "shape=$shape",
            "colors=$colors",
            "elevation=$elevation",
            "border=$border",
            "contentPadding=$contentPadding",
            "interactionSource=$interactionSource",
            "content=$content"
        ).joinToString(", ", "Button args : ")
    )
    require(1 == listOfNotNull(state.content, content).size) {
        "enable only one content : state.content=${state.content}, content=$content"
    }

    val actualModifier = if (modifier.hasTestTag()) {
        modifier
    } else {
        modifier.testTag(state.testTag)
    }

    androidx.compose.material3.Button(
        onClick = state.onClick,
        modifier = actualModifier,
        enabled = state.enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource
    ) {
        state.content.let {
            if (null != it) {
                StateComponent(it)
            } else {
                content!!()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewButton(@PreviewParameter(ButtonStateProvider::class) state: ButtonState) {
    MaterialTheme {
        if (null == state.content) {
            Button(state = state, content = { Text(TextState("button label")) })
        } else {
            Button(state = state)
        }
    }
}