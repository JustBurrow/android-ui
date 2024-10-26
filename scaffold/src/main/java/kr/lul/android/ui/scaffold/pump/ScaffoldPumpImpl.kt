package kr.lul.android.ui.scaffold.pump

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kr.lul.android.ui.scaffold.state.BottomState
import kr.lul.android.ui.scaffold.state.FabState
import kr.lul.android.ui.scaffold.state.SnackbarState
import kr.lul.android.ui.scaffold.state.TopState
import kr.lul.android.ui.viewmodel.base.PriorityProgressPump

class ScaffoldPumpImpl : PriorityProgressPump(), ScaffoldPump {
    companion object {
        private const val TAG = "ScaffoldPumpImpl"
    }

    private val _top = MutableStateFlow(TopState.NONE)
    override val top: StateFlow<TopState> = _top

    private val _bottom = MutableStateFlow(BottomState.NONE)
    override val bottom: StateFlow<BottomState> = _bottom

    private val _snackbar = MutableStateFlow(SnackbarState.NONE)
    override val snackbar: StateFlow<SnackbarState> = _snackbar

    private val _fab = MutableStateFlow(FabState.NONE)
    override val fab: StateFlow<FabState> = _fab

    override fun pump(top: TopState?, bottom: BottomState?, snackbar: SnackbarState?, fab: FabState?) {
        if (null != top) {
            _top.update { top }
        }
        if (null != bottom) {
            _bottom.update { bottom }
        }
        if (null != snackbar) {
            _snackbar.update { snackbar }
        }
        if (null != fab) {
            _fab.update { fab }
        }
    }

    override fun toString() = listOf(
        "top=${top.value}",
        "bottom=${bottom.value}",
        "snackbar=${snackbar.value}",
        "fab=${fab.value}",
        "progress=${progress.value}"
    ).joinToString(", ", "$TAG(", ")")
}