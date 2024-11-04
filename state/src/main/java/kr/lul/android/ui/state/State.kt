package kr.lul.android.ui.state

import androidx.compose.runtime.Immutable

/**
 * 상태 홀더의 기반.
 */
@Immutable
interface State {
    /**
     * 상태를 식별하는 키.
     * `androidx.compose.foundation.lazy.LazyListScope.item`의 `key`등에 사용.
     */
    val key: Any

    /**
     * [androidx.compose.ui.platform.testTag]용 태그.
     *
     * @see androidx.compose.ui.platform.testTag
     */
    val testTag: String
}