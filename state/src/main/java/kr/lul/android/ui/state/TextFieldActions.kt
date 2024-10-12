package kr.lul.android.ui.state

import androidx.compose.runtime.Immutable
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.text.input.TextFieldValue

/**
 * 텍스트 필드의 사용자 조작을 처리할 수 있는 함수를 모아둔 클래스.
 */
@Immutable
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
        /**
         * 기능이 없는 빈 인스턴스.
         */
        val EMPTY = TextFieldActions(onValueChange = {})
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
    ) = TextFieldActions(
        onValueChange = if (null == additionalOnValueChange) {
            onValueChange
        } else {
            {
                try {
                    onValueChange(it)
                } finally {
                    additionalOnValueChange(it)
                }
            }
        },
        onFocusChange = when {
            null == onFocusChange && null == additionalOnFocusChange ->
                null

            null == onFocusChange && null != additionalOnFocusChange ->
                additionalOnFocusChange

            null != onFocusChange && null == additionalOnFocusChange ->
                onFocusChange

            else -> { focus ->
                try {
                    onFocusChange!!(focus)
                } finally {
                    additionalOnFocusChange!!(focus)
                }
            }
        }
    )

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