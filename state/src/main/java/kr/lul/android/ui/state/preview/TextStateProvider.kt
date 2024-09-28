package kr.lul.android.ui.state.preview

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.sp
import kr.lul.android.ui.state.TextState

class TextStateProvider : PreviewParameterProvider<TextState> {
    override val values = sequenceOf(
        TextState(text = ""),
        TextState(text = "Hello, World!"),
        TextState(
            text = "안녕하세요!",
            color = Color.Black,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontFamily = null,
            letterSpacing = 3.sp,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        ),
    )
}