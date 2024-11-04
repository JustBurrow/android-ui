package kr.lul.android.ui.scaffold.state.bottom

import androidx.compose.runtime.Immutable
import kr.lul.android.ui.state.IconState
import kr.lul.android.ui.state.State
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * 하단 내비게이션 바의 항목.
 */
@Immutable
@OptIn(ExperimentalUuidApi::class)
class BottomNavigationItem(
    /**
     * 항목의 아이콘.
     */
    val icon: IconState? = null,
    /**
     * 항목의 이름(레이블).
     */
    val label: IconState? = null,
    /**
     * 클릭할 수 있는 항목이면 `true`.
     */
    val enable: Boolean = true,
    /**
     * 현재 선택된 항목이면 `true`.
     */
    val current: Boolean = false,
    override val key: Any = Uuid.random(),
    override val testTag: String = key.toString()
) : State {
    init {
        require(icon != null || label != null) { "icon and label must not be null at the same time" }
    }

    fun copy(
        icon: IconState? = this.icon,
        label: IconState? = this.label,
        enable: Boolean = this.enable,
        current: Boolean = this.current,
    ) = BottomNavigationItem(
        icon = icon,
        label = label,
        enable = enable,
        current = current,
        key = key,
        testTag = testTag
    )

    override fun equals(other: Any?) = this === other || (
            other is BottomNavigationItem &&
                    icon == other.icon &&
                    label == other.label &&
                    enable == other.enable &&
                    current == other.current &&
                    key == other.key &&
                    testTag == other.testTag
            )

    override fun hashCode(): Int {
        var result = icon?.hashCode() ?: 0
        result = 31 * result + (label?.hashCode() ?: 0)
        result = 31 * result + enable.hashCode()
        result = 31 * result + current.hashCode()
        result = 31 * result + key.hashCode()
        result = 31 * result + testTag.hashCode()
        return result
    }

    override fun toString() = listOf(
        "icon=$icon",
        "label=$label",
        "enable=$enable",
        "current=$current",
        "key=$key",
        "testTag='$testTag'"
    ).joinToString(", ", "BottomNavigationItem(", ")")
}