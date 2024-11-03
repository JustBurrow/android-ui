package kr.lul.android.ui.state

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ButtonStateProvider : PreviewParameterProvider<ButtonState> {
    override val values = sequenceOf(
        ButtonState(enabled = true, content = null, onClick = {}),
        ButtonState(enabled = false, content = null, onClick = {}),
        ButtonState(enabled = true, content = TextState("Enabled w/ content"), onClick = {}),
        ButtonState(enabled = false, content = TextState("Disabled w/ content"), onClick = {}),
        ButtonState(enabled = true, content = IconState(drawable = android.R.drawable.star_on), onClick = {}),
        ButtonState(enabled = false, content = IconState(drawable = android.R.drawable.stat_notify_error), onClick = {})
    )
}