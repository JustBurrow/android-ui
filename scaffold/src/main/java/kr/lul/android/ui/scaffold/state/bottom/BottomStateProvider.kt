package kr.lul.android.ui.scaffold.state.bottom

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class BottomStateProvider : PreviewParameterProvider<BottomState> {
    override val values: Sequence<BottomState> = TextBottomStateProvider().values
}