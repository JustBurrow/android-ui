package kr.lul.android.ui.viewmodel.base

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel(
    protected val tag: String
) : ViewModel(), DefaultLifecycleObserver {
    init {
        Log.d(tag, "#init called.")
    }

    /**
     * UI 스레드에서 실행되는 코루틴을 시작한다.
     *
     * @return [Job] 코루틴의 실행을 관리하는 객체.
     */
    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        Log.d(tag, "#launch args : context=$context, start=$start, block=$block")

        val job = viewModelScope.launch(context, start, block)

        Log.d(tag, "#launch return : $job")
        return job
    }

    /**
     * UI 스레드에서 실행되는 코루틴을 시작한다.
     *
     * @return [Deferred] 코루틴의 실행을 관리하고 결과를 받는 객체.
     */
    protected fun <T> async(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> T
    ): Deferred<T> {
        Log.d(tag, "#async args : context=$context, start=$start, block=$block")

        val deferred = viewModelScope.async(context, start, block)

        Log.d(tag, "#async return : $deferred")
        return deferred
    }

    override fun onCreate(owner: LifecycleOwner) {
        Log.d(tag, "#onCreate args : owner=$owner")
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.d(tag, "#onStart args : owner=$owner")
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.d(tag, "#onResume args : owner=$owner")
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.d(tag, "#onPause args : owner=$owner")
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d(tag, "#onStop args : owner=$owner")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.d(tag, "#onDestroy args : owner=$owner")
    }

    override fun onCleared() {
        Log.d(tag, "#onCleared called.")
    }
}