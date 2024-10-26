package kr.lul.android.ui.scaffold.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.lul.android.ui.viewmodel.base.ProgressPump
import javax.inject.Inject

/**
 * [kr.lul.android.ui.scaffold.compose.Scaffold]의 상태를 관리하는 VM.
 *
 * 관리 항목 :
 * 1. 진행 상태.
 */
@HiltViewModel
class ScaffoldViewModel @Inject constructor(
    val progress: ProgressPump
) : ViewModel(), DefaultLifecycleObserver {
    companion object {
        private const val TAG = "ScaffoldViewModel"
    }

    override fun toString() = listOf(
        "progress=$progress"
    ).joinToString(", ", "$TAG(", ")")
}