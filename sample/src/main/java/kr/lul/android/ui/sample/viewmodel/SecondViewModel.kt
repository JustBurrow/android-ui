package kr.lul.android.ui.sample.viewmodel

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kr.lul.android.ui.state.BlockingProgressState
import kr.lul.android.ui.state.NonBlockingProgressState
import kr.lul.android.ui.viewmodel.base.BaseViewModel
import kr.lul.android.ui.viewmodel.base.ProgressViewModelet
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    override val progress: ProgressViewModelet
) : BaseViewModel("SecondViewModel") {
    fun onClickBlocking() {
        Log.d(tag, "#onClickBlocking called.")

        launch(BlockingProgressState) {
            delay(1000)
        }
    }

    fun onClickNonBlocking() {
        Log.d(tag, "#onClickNonBlocking called.")

        launch(NonBlockingProgressState) {
            delay(3000)
        }
    }
}
