package kr.lul.android.ui.state

/**
 * 기본적인 로딩 상태.
 * 사용자의 조직이 가능한 로딩 중 표시.
 */
object NonBlockingLoadingState : LoadingState {
    override val priority = LoadingState.PRIORITY_NON_BLOCKING

    override fun toString() = "NonBlockingLoadingState($priority)"
}

/**
 * 기본적인 로딩 상태.
 * 사용자의 조작이 불가능한 로딩 중.
 */
object BlockingLoadingState : LoadingState {
    override val priority = LoadingState.PRIORITY_BLOCKING

    override fun toString() = "BlockingLoadingState($priority)"
}