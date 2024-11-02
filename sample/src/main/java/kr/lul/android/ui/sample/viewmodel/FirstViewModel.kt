package kr.lul.android.ui.sample.viewmodel

import android.util.Log
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kr.lul.android.ui.scaffold.state.bottom.TextBottomState
import kr.lul.android.ui.scaffold.state.top.TextTopState
import kr.lul.android.ui.scaffold.viewmodel.ScaffoldContentViewModel
import kr.lul.android.ui.state.BlockingProgressState
import kr.lul.android.ui.state.NonBlockingProgressState
import kr.lul.android.ui.state.TextState
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor() : ScaffoldContentViewModel("FirstViewModel") {
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

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        Log.i(tag, "#onCreate : this=$this")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)

        scaffoldPump.pump(
            top = TextTopState(TextState(text = "$tag.top", textAlign = TextAlign.Center)),
            bottom = TextBottomState(TextState(text = "$tag.bottom", textAlign = TextAlign.End))
        )
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun toString() = listOf(
        "hashCode=${hashCode().toHexString(HexFormat.Default).uppercase(Locale.getDefault())}",
    ).joinToString(", ", "$tag(", ")")
}