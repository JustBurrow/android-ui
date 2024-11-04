package kr.lul.android.ui.scaffold.state.bottom

import androidx.compose.runtime.Immutable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * 하단 내비게이션 바.
 */
@OptIn(ExperimentalUuidApi::class)
@Immutable
class NavigationBottomState(
    val items: List<BottomNavigationItem>,
    override val key: Any = Uuid.random(),
    override val testTag: String = key.toString()
) : BottomState {
    init {
        require(items.isNotEmpty()) { "items must not be empty" }
    }

    fun copy(
        actions: List<BottomNavigationItem> = this.items
    ) = NavigationBottomState(actions, key, testTag)

    override fun equals(other: Any?) = this === other || (
            other is NavigationBottomState &&
                    items == other.items &&
                    key == other.key &&
                    testTag == other.testTag
            )

    override fun hashCode(): Int {
        var result = items.hashCode()
        result = 31 * result + key.hashCode()
        result = 31 * result + testTag.hashCode()
        return result
    }

    override fun toString() = listOf(
        "items=$items",
        "key=$key",
        "testTag='$testTag'"
    ).joinToString(", ", "NavigationBottomState(", ")")
}