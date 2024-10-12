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
        /**
         * 기능이 없는 빈 인스턴스.
         */
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

    /**
     * 현재 인스턴스의 사용자 조작을 처리한 후에 추가로 처리할 사용자 조작을 정의한다.
     *
     * @param additionalHandler 추가로 처리할 사용자 조작.
     */
    fun then(additionalHandler: TextFieldActionHandler) = object : TextFieldActionHandler {
        override fun onValueChange(value: TextFieldValue) {
            try {
                this@TextFieldActionHandler.onValueChange(value)
            } finally {
                additionalHandler.onValueChange(value)
            }
        }

        override fun onFocusChange(focus: FocusState) {
            try {
                this@TextFieldActionHandler.onFocusChange(focus)
            } finally {
                additionalHandler.onFocusChange(focus)
            }
        }
    }

    /**
     * 현재 인스턴스의 사용자 조작을 처리한 후에 추가로 처리할 사용자 조작을 정의한다.
     *
     * @param additionalOnValueChange 추가로 처리할 값 변경 이벤트 처리.
     * @param additionalOnFocusChange 추가로 처리할 포커스 변경 이벤트 처리.
     */
    fun then(
        additionalOnValueChange: ((TextFieldValue) -> Unit)? = null,
        additionalOnFocusChange: ((FocusState) -> Unit)? = null
    ) = object : TextFieldActionHandler {
        override fun onValueChange(value: TextFieldValue) {
            try {
                this@TextFieldActionHandler.onValueChange(value)
            } finally {
                additionalOnValueChange?.invoke(value)
            }
        }

        override fun onFocusChange(focus: FocusState) {
            try {
                this@TextFieldActionHandler.onFocusChange(focus)
            } finally {
                additionalOnFocusChange?.invoke(focus)
            }
        }
    }
}