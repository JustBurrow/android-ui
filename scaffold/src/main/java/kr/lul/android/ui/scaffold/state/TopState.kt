package kr.lul.android.ui.scaffold.state

/**
 * [androidx.compose.material3.Scaffold]의 상단 바 상태.
 */
interface TopState {
    companion object {
        /**
         * 상단바 표시 안함.
         */
        val NONE = object : TopState {}
    }
}
