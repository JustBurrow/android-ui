package kr.lul.android.ui.state

import androidx.compose.runtime.Immutable

/**
 * 상태 홀더의 기반.
 */
@Immutable
interface State {
    /**
     * [androidx.compose.ui.platform.testTag]용 태그.
     *
     * @see androidx.compose.ui.platform.testTag
     */
    val testTag: String
}