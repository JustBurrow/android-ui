package kr.lul.android.ui.scaffold.state.bottom

import kr.lul.android.ui.state.State

/**
 * [androidx.compose.material3.Scaffold]의 하단 바 상태.
 *
 * [Bottom app bar](https://m3.material.io/components/bottom-app-bar/overview)의 상태 홀더.
 */
interface BottomState : State {
    companion object {
        /**
         * 하단바 표시 안함.
         */
        val NONE = object : BottomState {
            override val key = "BottomState.NONE"
            override val testTag = key
            override fun toString() = key
        }
    }
}
