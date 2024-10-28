package kr.lul.android.ui.scaffold.state

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.sp
import kr.lul.android.ui.scaffold.state.top.IconTopState
import kr.lul.android.ui.scaffold.state.top.TextTopState
import kr.lul.android.ui.state.IconState
import kr.lul.android.ui.state.TextState

class ScaffoldStateProvider : PreviewParameterProvider<ScaffoldState> {
    override val values = sequenceOf(
        ScaffoldState(),
        ScaffoldState(
            top = TextTopState(
                TextState(
                    text = "top bar",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        lineHeight = 36.sp,
                        letterSpacing = 0.sp
                    )
                )
            )
        ),
        ScaffoldState(top = IconTopState(IconState(drawable = android.R.drawable.ic_secure))),
    )
}