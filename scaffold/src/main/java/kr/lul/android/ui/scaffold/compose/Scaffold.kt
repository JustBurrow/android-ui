package kr.lul.android.ui.scaffold.compose

import android.util.Log
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import kr.lul.android.ui.navigation.navigator.BaseNavigator
import kr.lul.android.ui.scaffold.state.BottomState
import kr.lul.android.ui.scaffold.state.FabState
import kr.lul.android.ui.scaffold.state.ScaffoldState
import kr.lul.android.ui.scaffold.state.SnackbarState
import kr.lul.android.ui.scaffold.state.TopState

private const val TAG = "ui.scaffold"

/**
 * [androidx.compose.material3.Scaffold]를 확장해서 기본적인 기능을 제공한다.
 *
 * 1. 내비게이션 관리.
 *
 * @param baseNavigator 공통 내비게이션.
 * @param state 스캐폴드  상태.
 * @param modifier
 * @param topBar 상단 바 UI.
 * @param bottomBar 하단 바 UI.
 * @param snackbarHost 스낵바 UI.
 * @param floatingActionButton 플로팅 액션 버튼 UI.
 * @param floatingActionButtonPosition 플로팅 액션 버튼 위치.
 * @param containerColor 컨테이너 색상.
 * @param contentColor 컨텐츠 색상.
 * @param contentWindowInsets 컨텐츠 윈도우 인셋.
 *
 * @see androidx.compose.material3.Scaffold
 */
@Composable
fun Scaffold(
    baseNavigator: BaseNavigator,
    state: ScaffoldState,
    modifier: Modifier = Modifier,
    topBar: @Composable (TopState) -> Unit = {},
    bottomBar: @Composable (BottomState) -> Unit = {},
    snackbarHost: @Composable (SnackbarState) -> Unit = {},
    floatingActionButton: @Composable (FabState) -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    builder: NavGraphBuilder.() -> Unit
) {
    Log.v(
        TAG,
        listOf(
            "baseNavigator=$baseNavigator",
            "modifier=$modifier",
            "topBar=$topBar",
            "bottomBar=$bottomBar",
            "snackbarHost=$snackbarHost",
            "floatingActionButton=$floatingActionButton",
            "floatingActionButtonPosition=$floatingActionButtonPosition",
            "containerColor=$containerColor",
            "contentColor=$contentColor",
            "contentWindowInsets=$contentWindowInsets",
            "builder=$builder"
        ).joinToString(", ", "#Scaffold args : ")
    )

    androidx.compose.material3.Scaffold(
        modifier = modifier,
        topBar = { topBar(state.top) },
        bottomBar = { bottomBar(state.bottom) },
        snackbarHost = { snackbarHost(state.snackbar) },
        floatingActionButton = { floatingActionButton(state.fab) },
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets
    ) {
        NavHost(
            navController = baseNavigator.navController,
            startDestination = baseNavigator.destination.routePattern,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            builder = builder
        )
    }
}