package kr.lul.android.ui.state

import androidx.compose.runtime.Immutable
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.text.input.TextFieldValue

/**
 * 텍스트 필드의 사용자 조작을 처리할 수 있는 함수를 모아둔 클래스.
 */
@Immutable
interface TextFieldActionHandler {
    companion object {
        val EMPTY = object : TextFieldActionHandler {}
    }

    /**
     * 텍스트 필드 값 변경 이벤트 처리.
     *
     * @param value 변경된 값.
     */
    fun onValueChange(value: TextFieldValue) {}

    /**
     * 포커스 변경 이벤트 처리.
     *
     * @param focus 포커스 상태.
     */
    fun onFocusChange(focus: FocusState) {}
}