package kr.lul.android.ui.scaffold.state.dev

import androidx.compose.runtime.Immutable
import kr.lul.android.ui.state.IconState
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * 클릭 가능한 요소가 1개인 개발자 도구 상태.
 */
@Immutable
@OptIn(ExperimentalUuidApi::class)
class ClickableDevState(
    override val show: Boolean,
    /**
     * 클릭 가능한 요소(아이콘).
     */
    val icon: IconState,
    val onClick: () -> Unit,
    override val key: Any = Uuid.random(),
    override val testTag: String = key.toString()
) : DevState {
    fun copy(
        show: Boolean = this.show,
        icon: IconState = this.icon,
    ) = ClickableDevState(show, icon, onClick, key, testTag)

    override fun equals(other: Any?) = this === other || (
            other is ClickableDevState &&
                    show == other.show &&
                    icon == other.icon &&
                    onClick == other.onClick &&
                    key == other.key &&
                    testTag == other.testTag
            )

    override fun hashCode(): Int {
        var result = show.hashCode()
        result = 31 * result + icon.hashCode()
        result = 31 * result + onClick.hashCode()
        result = 31 * result + key.hashCode()
        result = 31 * result + testTag.hashCode()
        return result
    }

    override fun toString() =
        "ClickableDevState(show=$show, icon=$icon, onClick=$onClick, key=$key, testTag='$testTag')"
}