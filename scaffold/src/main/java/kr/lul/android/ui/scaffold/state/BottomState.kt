package kr.lul.android.ui.scaffold.state

/**
 * [androidx.compose.material3.Scaffold]의 하단 바 상태.
 */
interface BottomState {
    companion object {
        /**
         * 하단바 표시 안함.
         */
        val NONE = object : BottomState {
            override fun toString() = "EmptyBottomState"
        }
    }
}
