package kr.lul.android.ui.sample.viewmodel

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kr.lul.android.ui.sample.ui.state.NavigationBottomState
import kr.lul.android.ui.scaffold.BuildConfig
import kr.lul.android.ui.scaffold.state.MessageSnackbarState
import kr.lul.android.ui.scaffold.state.dev.ClickableDevState
import kr.lul.android.ui.scaffold.state.fab.IconFabState
import kr.lul.android.ui.scaffold.state.top.TextTopState
import kr.lul.android.ui.scaffold.viewmodel.ScaffoldContentViewModel
import kr.lul.android.ui.state.BlockingProgressState
import kr.lul.android.ui.state.IconState
import kr.lul.android.ui.state.NonBlockingProgressState
import kr.lul.android.ui.state.TextState
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor() : ScaffoldContentViewModel("FirstViewModel") {
    private val _fabCounter = MutableStateFlow(0)
    val fabCounter: StateFlow<Int> = _fabCounter

    fun onClickBlocking() {
        Log.d(tag, "#onClickBlocking called.")

        launch(BlockingProgressState) {
            delay(1000)
        }
    }

    fun onClickNonBlocking() {
        Log.d(tag, "#onClickNonBlocking called.")

        launch(NonBlockingProgressState) {
            delay(5000)
        }
    }

    fun onClickFab() {
        _fabCounter.update {
            val next = if (Int.MAX_VALUE > it) {
                it + 1
            } else {
                Int.MAX_VALUE
            }
            scaffoldPump.pump(snackbar = MessageSnackbarState("Click : $next", "FAB clicked"))
            Log.d(tag, "#onClickFab : $it => $next")
            next
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        Log.i(tag, "#onCreate : this=$this")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)

        scaffoldPump.pump(
            top = TextTopState(TextState(text = "$tag.top", textAlign = TextAlign.Center)),
            bottom = NavigationBottomState,
            fab = IconFabState(
                icon = IconState(drawable = android.R.drawable.ic_input_add, tint = Color.Cyan),
                onClick = ::onClickFab
            ),
            dev = ClickableDevState(
                show = true,
                icon = IconState(imageVector = Icons.Default.Add, tint = Color.Red),
                onClick = {
                    if (BuildConfig.DEBUG) {
                        _fabCounter.update {
                            Log.d(tag, "#dev.onClick : $it => ${Int.MAX_VALUE}")
                            Int.MAX_VALUE
                        }
                    }
                }
            )
        )
    }

    override fun toString() = listOf(
        super.toString(),
        "fabCounter=${fabCounter.value}",
    ).joinToString(", ", "$tag(", ")")
}