package kr.lul.android.ui.scaffold.state.top

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import kr.lul.android.ui.state.TextState

/**
 * 상단바에 문자열을 표시하는 상태.
 */
@Immutable
data class TextTopState(
    val text: TextState,
    val horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    val verticalAlignment: Alignment.Vertical = Alignment.CenterVertically
) : TopState {
    constructor(
        text: String,
        horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
        verticalAlignment: Alignment.Vertical = Alignment.CenterVertically
    ) : this(TextState(text), horizontalArrangement, verticalAlignment)
}