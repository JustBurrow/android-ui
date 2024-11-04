package kr.lul.android.ui.scaffold.state

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Immutable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * 기본적인 스낵바 상태.
 */
@Immutable
@OptIn(ExperimentalUuidApi::class)
class MessageSnackbarState(
    val message: String,
    val actionLabel: String? = null,
    val withDismissAction: Boolean = false,
    val duration: SnackbarDuration = SnackbarDuration.Short,
    override val key: Any = Uuid.random(),
    override val testTag: String = key.toString()
) : SnackbarState {
    override fun equals(other: Any?) = this === other || (
            other is MessageSnackbarState &&
                    message == other.message &&
                    actionLabel == other.actionLabel &&
                    withDismissAction == other.withDismissAction &&
                    duration == other.duration &&
                    key == other.key &&
                    testTag == other.testTag
            )

    override fun hashCode(): Int {
        var result = message.hashCode()
        result = 31 * result + (actionLabel?.hashCode() ?: 0)
        result = 31 * result + withDismissAction.hashCode()
        result = 31 * result + duration.hashCode()
        result = 31 * result + key.hashCode()
        result = 31 * result + testTag.hashCode()
        return result
    }

    override fun toString() = listOf(
        "message=$message",
        "actionLabel=$actionLabel",
        "withDismissAction=$withDismissAction",
        "duration=$duration",
        "key=$key",
        "testTag=$testTag"
    ).joinToString(", ", "MessageSnackbarState(", ")")
}