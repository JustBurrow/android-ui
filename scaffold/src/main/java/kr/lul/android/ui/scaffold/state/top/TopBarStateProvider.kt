package kr.lul.android.ui.scaffold.state.top

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class TopBarStateProvider : PreviewParameterProvider<TopState> {
    override val values: Sequence<TopState> = TextTopStateProvider().values +
            IconTopStateProvider().values
}