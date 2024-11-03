package kr.lul.android.ui.scaffold.state.dev

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kr.lul.android.ui.state.IconState

class ClickableDevStateProvider : PreviewParameterProvider<ClickableDevState> {
    override val values = sequenceOf(
        ClickableDevState(show = true, icon = IconState(drawable = android.R.drawable.ic_input_add), onClick = {}),
        ClickableDevState(show = false, icon = IconState(drawable = android.R.drawable.ic_input_add), onClick = {})
    )
}