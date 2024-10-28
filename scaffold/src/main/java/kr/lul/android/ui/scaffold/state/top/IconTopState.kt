package kr.lul.android.ui.scaffold.state.top

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import kr.lul.android.ui.state.IconState

@Immutable
class IconTopState(
    val icon: IconState,
    val horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    val verticalAlignment: Alignment.Vertical = Alignment.CenterVertically
) : TopState