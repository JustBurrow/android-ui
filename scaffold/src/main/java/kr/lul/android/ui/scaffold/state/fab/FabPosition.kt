package kr.lul.android.ui.scaffold.state.fab

/**
 * @see androidx.compose.material3.FabPosition
 */
enum class FabPosition(
    /**
     * 대응하는 material3의 인스턴스.
     */
    val material: androidx.compose.material3.FabPosition
) {
    /**
     * @see androidx.compose.material3.FabPosition.Start
     */
    START(androidx.compose.material3.FabPosition.Start),

    /**
     * @see androidx.compose.material3.FabPosition.Center
     */
    CENTER(androidx.compose.material3.FabPosition.Center),

    /**
     * @see androidx.compose.material3.FabPosition.End
     */
    END(androidx.compose.material3.FabPosition.End),

    /**
     * @see androidx.compose.material3.FabPosition.EndOverlay
     */
    END_OVERLAY(androidx.compose.material3.FabPosition.EndOverlay),
}