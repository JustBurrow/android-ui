package kr.lul.android.ui.scaffold.state

import androidx.compose.ui.tooling.preview.PreviewParameterProvider


class ScaffoldStateProvider : PreviewParameterProvider<ScaffoldState> {
    override val values = sequenceOf(
        ScaffoldState()
    )
}