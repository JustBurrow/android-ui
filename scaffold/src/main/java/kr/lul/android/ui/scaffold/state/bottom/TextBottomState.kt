package kr.lul.android.ui.scaffold.state.bottom

import androidx.compose.runtime.Immutable
import kr.lul.android.ui.state.TextState

/**
 * 단순하게 문자열을 표시하는 하단바 상태.
 */
@Immutable
data class TextBottomState(
    /**
     * 표시할 문자열.
     */
    val text: TextState = TextState("")
) : BottomState