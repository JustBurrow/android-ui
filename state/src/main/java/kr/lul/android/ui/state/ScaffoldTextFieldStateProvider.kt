package kr.lul.android.ui.state

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ScaffoldTextFieldStateProvider : PreviewParameterProvider<ScaffoldTextFieldState> {
    override val values = sequenceOf(
        ScaffoldTextFieldState(text = ""),
        ScaffoldTextFieldState(text = "Hello, World!"),
        ScaffoldTextFieldState(text = "Disabled", enabled = false),
        ScaffoldTextFieldState(text = "Read only", readOnly = true),
        ScaffoldTextFieldState(text = "", placeholder = TextState("Placeholder")),
        ScaffoldTextFieldState(
            text = "Hello, World!",
            leadingIcon = IconState(drawable = android.R.drawable.ic_media_play),
            trailingIcon = IconState(drawable = android.R.drawable.ic_media_pause)
        ),
        ScaffoldTextFieldState(text = "Hello, World!", prefix = TextState("Prefix"), suffix = TextState("Suffix")),
        ScaffoldTextFieldState(text = "With error", errorText = TextState("Error!", color = Color.Red)),
    )
}