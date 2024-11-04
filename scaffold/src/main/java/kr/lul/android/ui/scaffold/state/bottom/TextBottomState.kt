package kr.lul.android.ui.scaffold.state.bottom

import androidx.compose.runtime.Immutable
import kr.lul.android.ui.state.TextState
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * 단순하게 문자열을 표시하는 하단바 상태.
 */
@OptIn(ExperimentalUuidApi::class)
@Immutable
data class TextBottomState(
    /**
     * 표시할 문자열.
     */
    val text: TextState = TextState(""),
    override val key: Any = Uuid.random(),
    override val testTag: String = key.toString()
) : BottomState