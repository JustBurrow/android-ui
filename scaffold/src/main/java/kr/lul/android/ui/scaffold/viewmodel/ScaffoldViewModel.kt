package kr.lul.android.ui.scaffold.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kr.lul.android.ui.scaffold.pump.ScaffoldPump
import kr.lul.android.ui.scaffold.state.ScaffoldState
import javax.inject.Inject

/**
 * [kr.lul.android.ui.scaffold.compose.Scaffold]의 상태를 관리하는 VM.
 *
 * 관리 항목 :
 * 1. 진행 상태.
 */
@HiltViewModel
class ScaffoldViewModel @Inject constructor(
    private val pump: ScaffoldPump
) : ViewModel(), DefaultLifecycleObserver {
    companion object {
        private const val TAG = "ScaffoldViewModel"
    }

    val state = combine(
        pump.top,
        pump.bottom,
        pump.snackbar,
        pump.fab,
        pump.progress
    ) { top, bottom, snackbar, fab, progress ->
        ScaffoldState(
            top = top,
            bottom = bottom,
            snackbar = snackbar,
            fab = fab,
            progress = progress
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ScaffoldState())

    override fun toString() = listOf(
        "state=$state"
    ).joinToString(", ", "$TAG(", ")")
}