package kr.lul.android.ui.state

/**
 * 상태 홀더의 기반.
 */
interface State {
    /**
     * [androidx.compose.ui.platform.testTag]용 태그.
     *
     * @see androidx.compose.ui.platform.testTag
     */
    val testTag: String
}