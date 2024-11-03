package kr.lul.android.ui.state

/**
 * 다이얼로그 조작 콜백.
 *
 * @see androidx.compose.ui.window.Dialog
 */
interface DialogActionHandler {
    /**
     * 다이얼로그를 닫으려 할 때 호출할 콜백.
     */
    fun onDismissRequest()
}