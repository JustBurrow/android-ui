package kr.lul.android.ui.state.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kr.lul.android.ui.state.SingleTextLine
import kr.lul.android.ui.state.TextFieldState
import kr.lul.android.ui.state.TextLines

class TextFieldStateProvider : PreviewParameterProvider<TextFieldState> {
    override val values = sequenceOf(
        TextFieldState(text = ""),
        TextFieldState("with input"),
        TextFieldState("disabled", false),
        TextFieldState("readOnly", readOnly = true),
        TextFieldState("with error", error = true),
        TextFieldState("single line", lines = SingleTextLine),
        TextFieldState("multiple lines(3)", lines = TextLines(3))
    )
}