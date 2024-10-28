package kr.lul.android.ui.scaffold.state.top

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import kr.lul.android.ui.state.IconState

/**
 * 상단바에 아이콘을 표시하는 상태.
 */
@Immutable
class IconTopState(
    /**
     * 아이콘.
     */
    val icon: IconState,
    /**
     * 상단바의 배치.
     */
    val horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    /**
     * 상단바의 수직 정렬.
     */
    val verticalAlignment: Alignment.Vertical = Alignment.CenterVertically
) : TopState