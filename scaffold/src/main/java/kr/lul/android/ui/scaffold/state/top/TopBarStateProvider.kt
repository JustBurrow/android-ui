package kr.lul.android.ui.scaffold.state.top

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kr.lul.android.ui.state.IconState

class TopBarStateProvider : PreviewParameterProvider<TopState> {
    override val values: Sequence<TopState> = sequenceOf(
        TopState.NONE,
        IconTopState(IconState(drawable = android.R.drawable.ic_menu_help, tint = Color.Black))
    ) + TextTopStateProvider().values
}