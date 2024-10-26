package kr.lul.android.ui.scaffold.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import kr.lul.android.ui.navigation.navigator.BaseNavigator
import kr.lul.android.ui.scaffold.state.BottomState
import kr.lul.android.ui.scaffold.state.FabState
import kr.lul.android.ui.scaffold.state.ScaffoldState
import kr.lul.android.ui.scaffold.state.SnackbarState
import kr.lul.android.ui.scaffold.state.TopState
import kr.lul.android.ui.scaffold.viewmodel.ScaffoldViewModel
import kr.lul.android.ui.state.BlockingProgressState
import kr.lul.android.ui.state.NonBlockingProgressState

private const val TAG = "ui.scaffold"

/**
 * [androidx.compose.material3.Scaffold]를 확장해서 기본적인 기능을 제공한다.
 *
 * 1. 화면(page) 내비게이션.
 * 2. 진행 상태 UI.
 *
 * @param baseNavigator 공통 내비게이션.
 * @param viewModel 스캐폴드 뷰 모델.
 * @param modifier
 * @param topBar 상단 바 UI.
 * @param bottomBar 하단 바 UI.
 * @param snackbarHost 스낵바 UI.
 * @param floatingActionButton 플로팅 액션 버튼 UI.
 * @param floatingActionButtonPosition 플로팅 액션 버튼 위치.
 * @param containerColor 컨테이너 색상.
 * @param contentColor 컨텐츠 색상.
 * @param contentWindowInsets 컨텐츠 윈도우 인셋.
 * @param builder 내비게이션 그래프 빌더.
 *
 * @see androidx.compose.material3.Scaffold
 */
@Composable
fun Scaffold(
    baseNavigator: BaseNavigator,
    viewModel: ScaffoldViewModel = hiltViewModel(),
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
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        baseNavigator = baseNavigator,
        state = state,
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        builder = builder
    )
}

/**
 * [androidx.compose.material3.Scaffold]를 확장해서 기본적인 기능을 제공한다.
 *
 * 1. 화면(page) 내비게이션.
 * 2. 진행 상태 UI.
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
 * @param builder 내비게이션 그래프 빌더.
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
            "state=$state",
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
    if (state.progress.contains(BlockingProgressState)) {
        Dialog(onDismissRequest = {}) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp))
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(64.dp))
            }
        }
    }

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
        Box(modifier = modifier.padding(it)) {
            if (state.progress.contains(NonBlockingProgressState)) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .zIndex(1024F)
                )
            }
            NavHost(
                navController = baseNavigator.navController,
                startDestination = baseNavigator.destination.routePattern,
                modifier = Modifier
                    .fillMaxSize(),
                builder = builder
            )
        }
    }
}