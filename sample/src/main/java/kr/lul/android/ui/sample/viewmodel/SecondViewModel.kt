package kr.lul.android.ui.sample.viewmodel

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kr.lul.android.ui.state.BlockingLoadingState
import kr.lul.android.ui.state.NonBlockingLoadingState
import kr.lul.android.ui.viewmodel.base.BaseViewModel
import kr.lul.android.ui.viewmodel.base.LoadingViewModelet
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    override val loading: LoadingViewModelet
) : BaseViewModel("SecondViewModel") {
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
}
