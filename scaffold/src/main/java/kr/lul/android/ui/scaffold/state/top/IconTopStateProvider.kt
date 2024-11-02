package kr.lul.android.ui.scaffold.state.top

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kr.lul.android.ui.state.IconState

class IconTopStateProvider : PreviewParameterProvider<IconTopState> {
    override val values = sequenceOf(
        IconTopState(IconState(drawable = android.R.drawable.ic_menu_add))
    )
}