package kr.lul.android.ui.scaffold.state

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kr.lul.android.ui.scaffold.state.bottom.BottomState
import kr.lul.android.ui.scaffold.state.bottom.BottomStateProvider
import kr.lul.android.ui.scaffold.state.top.TopBarStateProvider
import kr.lul.android.ui.scaffold.state.top.TopState

class ScaffoldStateProvider : PreviewParameterProvider<ScaffoldState> {
    override val values = (sequenceOf(TopState.NONE) + TopBarStateProvider().values).map { top ->
        (sequenceOf(BottomState.NONE) + BottomStateProvider().values).map { bottom ->
            ScaffoldState(top, bottom)
        }
    }.flatten()
}