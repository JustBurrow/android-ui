package kr.lul.android.ui.state

/**
 * 로딩 상태.
 *
 * 사용자 UI에 표시하지 않을 경우엔 `null`을 사용한다.
 */
interface LoadingState : Comparable<LoadingState> {
    companion object {
        /**
         *
         */
        const val PRIORITY_NON_BLOCKING = 1024

        const val PRIORITY_BLOCKING = 0
    }

    /**
     * UI에 표시할 상태의 우선순위.
     *
     * 숫자가 작을수록 높은 우선순위를 가진다.
     */
    val priority: Int

    override fun compareTo(other: LoadingState) = priority - other.priority
}