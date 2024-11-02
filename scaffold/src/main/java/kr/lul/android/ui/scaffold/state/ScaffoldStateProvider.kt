package kr.lul.android.ui.scaffold.state

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kr.lul.android.ui.scaffold.state.bottom.BottomStateProvider
import kr.lul.android.ui.scaffold.state.fab.FabPosition
import kr.lul.android.ui.scaffold.state.fab.FabStateProvider
import kr.lul.android.ui.scaffold.state.top.TopBarStateProvider

class ScaffoldStateProvider : PreviewParameterProvider<ScaffoldState> {
    override val values = TopBarStateProvider().values.map { top ->
        BottomStateProvider().values.map { bottom ->
            FabStateProvider().values.map { fab ->
                FabPosition.entries.map { fabPosition ->
                    ScaffoldState(top = top, bottom = bottom, fab = fab, fabPosition = fabPosition)
                }
            }.flatten()
        }.flatten()
    }.flatten()
}