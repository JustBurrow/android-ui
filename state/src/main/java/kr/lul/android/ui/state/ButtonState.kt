package kr.lul.android.ui.state

import androidx.compose.runtime.Immutable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * 버튼 상태.
 *
 * @see androidx.compose.material3.Button
 */
@Immutable
@OptIn(ExperimentalUuidApi::class)
class ButtonState(
    val enabled: Boolean = true,
    /**
     * 버튼 내용.
     *
     * `null`이면 [kr.lul.android.ui.compose.Button]의 `content`를 사용함.
     *
     * @see kr.lul.android.ui.compose.StateComponent
     */
    val content: State? = null,
    override val testTag: String = Uuid.random().toString(),
    val onClick: () -> Unit = {}
) : State {
    fun copy(
        enabled: Boolean = this.enabled,
        content: State? = this.content
    ) = ButtonState(enabled, content, testTag, onClick)

    override fun equals(other: Any?) = this === other || (
            other is ButtonState &&
                    enabled == other.enabled &&
                    content == other.content &&
                    testTag == other.testTag &&
                    onClick == other.onClick
            )

    override fun hashCode(): Int {
        var result = enabled.hashCode()
        result = 31 * result + (content?.hashCode() ?: 0)
        result = 31 * result + testTag.hashCode()
        result = 31 * result + onClick.hashCode()
        return result
    }

    override fun toString() = listOf(
        "enabled=$enabled",
        "content=$content",
        "testTag='$testTag'",
        "onClick=$onClick"
    ).joinToString(", ", "ButtonState(", ")")
}
