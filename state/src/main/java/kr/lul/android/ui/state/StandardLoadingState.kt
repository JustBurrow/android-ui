package kr.lul.android.ui.state

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * 기본적인 진행 상태.
 * 사용자의 조직이 가능한 진행중 표시.
 */
@OptIn(ExperimentalUuidApi::class)
object NonBlockingProgressState : ProgressState {
    override val priority = 0
    override val key = Uuid.random()
    override val testTag = key.toString()
    override fun toString() = "NonBlockingProgressState(priority=$priority, key=$key, testTag='$testTag')"
}

/**
 * 기본적인 진행 상태.
 * 사용자의 조작이 불가능한 진행중.
 */
@OptIn(ExperimentalUuidApi::class)
object BlockingProgressState : ProgressState {
    override val priority = 0
    override val key = Uuid.random()
    override val testTag = key.toString()
    override fun toString() = "BlockingProgressState(priority=$priority, key=$key, testTag='$testTag')"
}