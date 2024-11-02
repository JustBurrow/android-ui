package kr.lul.android.ui.scaffold.state.top

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kr.lul.android.ui.state.preview.IconStateProvider

class IconTopStateProvider : PreviewParameterProvider<IconTopState> {
    override val values = IconStateProvider().values.map { IconTopState(icon = it) }
}