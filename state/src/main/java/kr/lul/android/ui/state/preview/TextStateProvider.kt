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
        TextState(text = "Hello, World!", textAlign = TextAlign.Center),
        TextState(text = "Hello, World!", textAlign = TextAlign.End),
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
        TextState("이게 이리 길어가 우얄꼬. 애린 왕자라도 불러봐야 하나, 보아뱀을 그려봐야 하나 궁시렁 구시렁 어절씨고 어쩌고 저저꼬 그렇고 말고. 여기다 아예 시를 하나 써볼까. 저작권이 문제겠지. 뭐가 있으려나 어휴... 뭐가 좋으려나 긴 예문이 하나 필요한데. 소스코드를 넣어버릴까? 그것도 좋긴 한데 재미는 별로 없을 것 같은데 뭐가 좋을까 모르겠네. 긴 거 하고 나면 여러 줄 예문도 필요한데 그건 또 뭘 찾아 넣어야 하나.")
    )
}