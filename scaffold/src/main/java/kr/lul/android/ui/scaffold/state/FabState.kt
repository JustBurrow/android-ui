package kr.lul.android.ui.scaffold.state

/**
 * [androidx.compose.material3.Scaffold]의 플로팅 액션 버튼 상태.
 *
 * @see androidx.compose.material3.Scaffold
 */
interface FabState {
    companion object {
        /**
         * 플로팅 액션 버튼 표시 안함.
         */
        val NONE = object : FabState {
            override fun toString() = "EmptyFabState"
        }
    }
}
