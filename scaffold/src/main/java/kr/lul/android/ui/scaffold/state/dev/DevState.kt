package kr.lul.android.ui.scaffold.state.dev

import androidx.compose.runtime.Immutable
import kr.lul.android.ui.state.State
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * 개발용 UI 상태.
 */
@Immutable
interface DevState : State {
    companion object {
        /**
         * 개발용 UI를 표지하지 않으며, 조작할 수도 없다.
         */
        @OptIn(ExperimentalUuidApi::class)
        val NONE = object : DevState {
            override val show = false
            override val testTag = Uuid.random().toString()
            override fun toString() = "DevState.NONE"
        }
    }

    /**
     * 개발용 UI를 표시한다.
     */
    val show: Boolean
}