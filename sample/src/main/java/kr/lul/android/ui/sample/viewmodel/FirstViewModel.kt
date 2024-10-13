package kr.lul.android.ui.sample.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kr.lul.android.ui.state.BlockingLoadingState
import kr.lul.android.ui.state.NonBlockingLoadingState
import kr.lul.android.ui.viewmodel.base.BaseViewModel
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor() : BaseViewModel("FirstViewModel") {
    fun onClickBlocking() {
        Log.d(tag, "#onClickBlocking called.")

        launch(BlockingLoadingState) {
            delay(1000)
        }
    }

    fun onClickNonBlocking() {
        Log.d(tag, "#onClickNonBlocking called.")

        launch(NonBlockingLoadingState) {
            delay(3000)
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        Log.i(tag, "#onCreate : this=$this")
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun toString() = listOf(
        "hashCode=${hashCode().toHexString(HexFormat.Default).uppercase(Locale.getDefault())}",
    ).joinToString(", ", "$tag(", ")")
}