package kr.lul.android.ui.scaffold.state

/**
 * [androidx.compose.material3.Scaffold]의 스낵바 상태.
 *
 * @see androidx.compose.material3.Scaffold
 */
interface SnackbarState {
    companion object {
        /**
         * 스낵바 표시 안함.
         */
        val NONE = object : SnackbarState {}
    }
}
