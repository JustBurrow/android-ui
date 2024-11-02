package kr.lul.android.ui.scaffold.state.fab

import androidx.compose.runtime.Immutable
import kr.lul.android.ui.state.IconState

/**
 * [androidx.compose.material3.IconButton]을 하나 표시하는 FAB의 상태.
 */
@Immutable
class IconFabState(
    /**
     * 버튼 아이콘.
     */
    val icon: IconState,
    override val onClick: () -> Unit
) : FabState {
    fun copy(icon: IconState = this.icon) = IconFabState(icon, onClick)

    override fun toString() = listOf(
        "icon=$icon",
        "onClick=$onClick"
    ).joinToString(", ", "IconFabState(", ")")
}