package kr.lul.android.ui.state

import androidx.compose.runtime.Stable
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.text.input.TextFieldValue

/**
 * 텍스트 필드의 사용자 조작을 처리할 수 있는 함수를 모아둔 클래스.
 */
@Stable
open class TextFieldActions(
    /**
     * 텍스트 필드 값 변경 이벤트 처리.
     */
    val onValueChange: (TextFieldValue) -> Unit,

    /**
     * 포커스 변경 이벤트 처리.
     */
    val onFocusChange: ((FocusState) -> Unit)? = null
) {
    companion object {
        val EMPTY = TextFieldActions(onValueChange = {})
    }

    override fun equals(other: Any?) = this === other || (
            other is TextFieldActions &&
                    onValueChange == other.onValueChange &&
                    onFocusChange == other.onFocusChange
            )

    override fun hashCode(): Int {
        var result = onValueChange.hashCode()
        result = 31 * result + (onFocusChange?.hashCode() ?: 0)
        return result
    }

    override fun toString() = listOf(
        "onValueChange=$onValueChange,",
        "onFocusChange=$onFocusChange",
    ).joinToString(", ", "TextFieldActions(", ")")
}