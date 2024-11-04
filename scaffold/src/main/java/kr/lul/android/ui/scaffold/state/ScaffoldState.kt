package kr.lul.android.ui.scaffold.state

import androidx.compose.runtime.Immutable
import kr.lul.android.ui.scaffold.state.bottom.BottomState
import kr.lul.android.ui.scaffold.state.dev.DevState
import kr.lul.android.ui.scaffold.state.fab.FabPosition
import kr.lul.android.ui.scaffold.state.fab.FabState
import kr.lul.android.ui.scaffold.state.top.TopState
import kr.lul.android.ui.state.ProgressState
import kr.lul.android.ui.state.State
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * [androidx.compose.material3.Scaffold]의 상태.
 *
 * @see androidx.compose.material3.Scaffold
 */
@Immutable
@OptIn(ExperimentalUuidApi::class)
class ScaffoldState(
    /**
     * 상단 바 상태.
     */
    val top: TopState = TopState.NONE,
    /**
     * 하단 바 상태.
     */
    val bottom: BottomState = BottomState.NONE,
    /**
     *  스낵바 상태.
     */
    val snackbar: SnackbarState = SnackbarState.NONE,
    /**
     * 플로팅 액션 버튼 상태.
     */
    val fab: FabState = FabState.NONE,
    /**
     * 플로팅 액션 버튼 위치.
     */
    val fabPosition: FabPosition = FabPosition.END,
    /**
     * 개발용 UI 상태.
     */
    val dev: DevState = DevState.NONE,
    /**
     * 진행 상태.
     */
    val progress: Set<ProgressState> = emptySet(),
    override val key: Any = Uuid.random(),
    override val testTag: String = key.toString()
) : State {
    fun copy(
        top: TopState = this.top,
        bottom: BottomState = this.bottom,
        snackbar: SnackbarState = this.snackbar,
        fab: FabState = this.fab,
        fabPosition: FabPosition = this.fabPosition,
        dev: DevState = this.dev,
        progress: Set<ProgressState> = this.progress,
    ) = ScaffoldState(top, bottom, snackbar, fab, fabPosition, dev, progress, key, testTag)

    override fun equals(other: Any?) = this === other || (
            other is ScaffoldState &&
                    top == other.top &&
                    bottom == other.bottom &&
                    snackbar == other.snackbar &&
                    fab == other.fab &&
                    fabPosition == other.fabPosition &&
                    dev == other.dev &&
                    progress == other.progress &&
                    key == other.key &&
                    testTag == other.testTag
            )

    override fun hashCode(): Int {
        var result = top.hashCode()
        result = 31 * result + bottom.hashCode()
        result = 31 * result + snackbar.hashCode()
        result = 31 * result + fab.hashCode()
        result = 31 * result + fabPosition.hashCode()
        result = 31 * result + dev.hashCode()
        result = 31 * result + progress.hashCode()
        result = 31 * result + key.hashCode()
        result = 31 * result + testTag.hashCode()
        return result
    }

    override fun toString() = listOf(
        "top=$top",
        "bottom=$bottom",
        "snackbar=$snackbar",
        "fab=$fab",
        "fabPosition=$fabPosition",
        "dev=$dev",
        "progress=$progress",
        "key=$key",
        "testTag='$testTag'"
    ).joinToString(", ", "ScaffoldState(", ")")
}
