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
import kr.lul.android.ui.state.ProgressState
import java.util.UUID
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * `@Composable baseViewModel`과 함께 사용해서 `ViewModel`의 공통적인 기능을 제공한다.
 *
 * 1. 라이프사이클 메서드.
 */
abstract class BaseViewModel(
    protected val tag: String,
    open val progress: ProgressViewModelet = PriorityProgressViewModelet()
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
        progress: ProgressState? = null,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        Log.v(tag, "#launch args : progress=$progress, context=$context, start=$start, block=$block")

        val key = UUID.randomUUID()
        if (null != progress) {
            this.progress.start(key, progress)
        }
        val job = viewModelScope.launch(context, start, block)
        if (null != progress) {
            job.invokeOnCompletion { e ->
                Log.i(tag, "#launch invokeOnCompletion : e=$e")
                this.progress.end(key)
            }
        }

        Log.v(tag, "#launch return : $job")
        return job
    }

    /**
     * UI 스레드에서 실행되는 코루틴을 시작한다.
     *
     * @return [Deferred] 코루틴의 실행을 관리하고 결과를 받는 객체.
     */
    protected fun <T> async(
        progress: ProgressState? = null,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> T
    ): Deferred<T> {
        Log.v(tag, "#async args : progress=$progress, context=$context, start=$start, block=$block")

        val key = UUID.randomUUID()
        if (null != progress) {
            this.progress.start(key, progress)
        }
        val deferred = viewModelScope.async(context, start, block)
        if (null != progress) {
            deferred.invokeOnCompletion { e ->
                Log.i(tag, "#async invokeOnCompletion : e=$e")
                this.progress.end(key)
            }
        }

        Log.v(tag, "#async return : $deferred")
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