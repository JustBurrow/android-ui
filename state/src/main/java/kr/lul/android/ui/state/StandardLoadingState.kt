package kr.lul.android.ui.state

/**
 * 기본적인 진행 상태.
 * 사용자의 조직이 가능한 진행중 표시.
 */
object NonBlockingProgressState : ProgressState {
    override val priority = ProgressState.PRIORITY_NON_BLOCKING

    override fun toString() = "NonBlockingProgressState($priority)"
}

/**
 * 기본적인 진행 상태.
 * 사용자의 조작이 불가능한 진행중.
 */
object BlockingProgressState : ProgressState {
    override val priority = ProgressState.PRIORITY_BLOCKING

    override fun toString() = "BlockingProgressState($priority)"
}