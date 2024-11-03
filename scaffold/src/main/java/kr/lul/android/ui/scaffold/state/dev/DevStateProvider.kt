package kr.lul.android.ui.scaffold.state.dev

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class DevStateProvider : PreviewParameterProvider<DevState> {
    override val values = ClickableDevStateProvider().values
}