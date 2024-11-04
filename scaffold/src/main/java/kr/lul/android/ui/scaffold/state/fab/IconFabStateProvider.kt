package kr.lul.android.ui.scaffold.state.fab

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kr.lul.android.ui.state.IconStateProvider

class IconFabStateProvider : PreviewParameterProvider<IconFabState> {
    override val values = IconStateProvider().values
        .map { IconFabState(icon = it, onClick = {}) }
}