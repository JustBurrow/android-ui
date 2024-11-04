package kr.lul.android.ui.scaffold.state

import kr.lul.android.ui.state.State
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * [androidx.compose.material3.Scaffold]의 스낵바 상태.
 *
 * @see androidx.compose.material3.Scaffold
 */
interface SnackbarState : State {
    companion object {
        /**
         * 스낵바 표시 안함.
         */
        @OptIn(ExperimentalUuidApi::class)
        val NONE = object : SnackbarState {
            override val key = Uuid.random()
            override val testTag = key.toString()
            override fun toString() = "SnackbarState.NONE"
        }
    }
}