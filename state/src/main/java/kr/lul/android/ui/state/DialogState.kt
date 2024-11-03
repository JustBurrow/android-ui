package kr.lul.android.ui.state

import androidx.compose.ui.window.DialogProperties

/**
 * 다이얼로그 상태.
 *
 * @see androidx.compose.ui.window.Dialog
 */
interface DialogState<S : State> : State {
    /**
     * 다이얼로그를 보여줄지 여부. `true`면 보여주고, `false`면 숨긴다.
     */
    val show: Boolean

    /**
     * 다이얼로그 속성.
     */
    val properties: DialogProperties

    /**
     * 다이얼로그 내용.
     *
     * TODO testTag 적용하기.
     */
    val content: S
}