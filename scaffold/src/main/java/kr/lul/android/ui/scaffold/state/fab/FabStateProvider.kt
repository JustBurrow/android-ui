package kr.lul.android.ui.scaffold.state.fab

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kr.lul.android.ui.state.IconState

class FabStateProvider : PreviewParameterProvider<FabState> {
    override val values = sequenceOf(
        FabState.NONE,
        IconFabState(icon = IconState(drawable = android.R.drawable.ic_menu_help), onClick = {})
    )
}