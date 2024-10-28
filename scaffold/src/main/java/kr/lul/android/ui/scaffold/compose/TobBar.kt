package kr.lul.android.ui.scaffold.compose

import android.util.Log
import androidx.compose.runtime.Composable
import kr.lul.android.ui.scaffold.compose.top.IconTopBar
import kr.lul.android.ui.scaffold.compose.top.TextTopBar
import kr.lul.android.ui.scaffold.state.top.IconTopState
import kr.lul.android.ui.scaffold.state.top.TextTopState
import kr.lul.android.ui.scaffold.state.top.TopState
import kotlin.reflect.KClass

object TobBarRegistry {
    private const val TAG = "TobBarRegistry"

    private val registry: MutableMap<KClass<out TopState>, @Composable (TopState) -> Unit> = mutableMapOf(
        TextTopState::class to { state -> TextTopBar(state as TextTopState) },
        IconTopState::class to { state -> IconTopBar(state as IconTopState) }
    )

    @Composable
    operator fun <T : TopState> get(state: T) {
        val topBar = registry[state::class] as @Composable (T) -> Unit
        Log.e(TAG, "#get args : state=$state, topBar=$topBar")
        topBar(state)
    }
}

@Composable
fun TopBar(state: TopState) {
    Log.v(TAG, "#TopBar args : state=$state")

    if (state == TopState.NONE) {
        // 비표시.
    } else {
        TobBarRegistry[state]
    }
}