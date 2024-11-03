package kr.lul.android.ui.scaffold.state.bottom

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kr.lul.android.ui.state.TextStateProvider

class TextBottomStateProvider : PreviewParameterProvider<TextBottomState> {
    override val values = TextStateProvider().values.map { TextBottomState(it) }
}