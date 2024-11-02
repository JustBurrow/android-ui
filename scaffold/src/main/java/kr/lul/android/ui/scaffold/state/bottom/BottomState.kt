package kr.lul.android.ui.scaffold.state.bottom

/**
 * [androidx.compose.material3.Scaffold]의 하단 바 상태.
 *
 * [Bottom app bar](https://m3.material.io/components/bottom-app-bar/overview)의 상태 홀더.
 */
interface BottomState {
    companion object {
        /**
         * 하단바 표시 안함.
         */
        val NONE = object : BottomState {
            override fun toString() = "BottomState.NONE"
        }
    }
}
