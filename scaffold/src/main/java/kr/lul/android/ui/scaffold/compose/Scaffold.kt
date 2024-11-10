package kr.lul.android.ui.scaffold.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kr.lul.android.ui.compose.BuildConfig
import kr.lul.android.ui.compose.Text
import kr.lul.android.ui.navigation.compose.PREVIEW_ROUTE_PATTERN
import kr.lul.android.ui.navigation.compose.rememberBaseNavigator
import kr.lul.android.ui.navigation.navigator.BaseNavigator
import kr.lul.android.ui.scaffold.compose.bottom.BottomBar
import kr.lul.android.ui.scaffold.compose.dev.Dev
import kr.lul.android.ui.scaffold.compose.fab.FloatingActionButton
import kr.lul.android.ui.scaffold.compose.snackbar.Snackbar
import kr.lul.android.ui.scaffold.compose.top.TopBar
import kr.lul.android.ui.scaffold.state.ScaffoldState
import kr.lul.android.ui.scaffold.state.ScaffoldStateProvider
import kr.lul.android.ui.scaffold.state.SnackbarState
import kr.lul.android.ui.scaffold.state.bottom.BottomState
import kr.lul.android.ui.scaffold.state.dev.DevState
import kr.lul.android.ui.scaffold.state.fab.FabState
import kr.lul.android.ui.scaffold.state.top.TopState
import kr.lul.android.ui.scaffold.viewmodel.ScaffoldViewModel
import kr.lul.android.ui.state.BlockingProgressState
import kr.lul.android.ui.state.NonBlockingProgressState
import kr.lul.android.ui.state.TextState
import kr.lul.android.ui.state.hasTestTag

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
 * @param fab 플로팅 액션 버튼 UI.
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
    topBar: @Composable (BaseNavigator, TopState) -> Unit = { baseNavigator, state ->
        TopBar(
            baseNavigator = baseNavigator,
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
    },
    bottomBar: @Composable (BaseNavigator, BottomState) -> Unit = { baseNavigator, state ->
        BottomBar(
            baseNavigator = baseNavigator,
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
    },
    snackbarHost: @Composable (SnackbarState) -> Unit = { Snackbar(it) },
    fab: @Composable (FabState) -> Unit = { FloatingActionButton(it) },
    dev: @Composable (DevState) -> Unit = {
        Dev(
            state = it,
            modifier = Modifier
                .padding(
                    top = WindowInsets.statusBars
                        .asPaddingValues()
                        .calculateTopPadding()
                )
                .zIndex(Z_INDEX_DEV)
        )
    },
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
        fab = fab,
        dev = dev,
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
 * @param fab 플로팅 액션 버튼 UI.
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
    topBar: @Composable (BaseNavigator, TopState) -> Unit = { baseNavigator, state ->
        TopBar(
            baseNavigator = baseNavigator,
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
    },
    bottomBar: @Composable (BaseNavigator, BottomState) -> Unit = { baseNavigator, state ->
        BottomBar(
            baseNavigator = baseNavigator,
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
    },
    snackbarHost: @Composable (SnackbarState) -> Unit = { Snackbar(it) },
    fab: @Composable (FabState) -> Unit = { FloatingActionButton(it) },
    dev: @Composable (DevState) -> Unit = {
        Dev(
            state = it,
            modifier = Modifier
                .padding(
                    top = WindowInsets.statusBars
                        .asPaddingValues()
                        .calculateTopPadding()
                )
                .zIndex(Z_INDEX_DEV)
        )
    },
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
            "fab=$fab",
            "dev=$dev",
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
                    .padding(32.dp)
                    .testTag(BlockingProgressState.testTag),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(64.dp))
            }
        }
    }

    Box(Modifier.fillMaxSize()) {
        if (BuildConfig.DEBUG && state.dev.show) {
            dev(state.dev)
        }

        val actualModifier = if (modifier.hasTestTag()) {
            modifier
        } else {
            modifier.testTag(state.testTag)
        }

        androidx.compose.material3.Scaffold(
            modifier = actualModifier.zIndex(Z_INDEX_SCAFFOLD),
            topBar = { topBar(baseNavigator, state.top) },
            bottomBar = { bottomBar(baseNavigator, state.bottom) },
            snackbarHost = { snackbarHost(state.snackbar) },
            floatingActionButton = { fab(state.fab) },
            floatingActionButtonPosition = state.fabPosition.material,
            containerColor = containerColor,
            contentColor = contentColor,
            contentWindowInsets = contentWindowInsets
        ) {
            Box(modifier = modifier.padding(it)) {
                if (state.progress.contains(NonBlockingProgressState)) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .zIndex(Z_INDEX_NON_BLOCKING_PROGRESS)
                            .testTag(NonBlockingProgressState.testTag)
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
}

@Composable
@Preview(showSystemUi = true)
internal fun PreviewScaffold(@PreviewParameter(ScaffoldStateProvider::class) state: ScaffoldState) {
    MaterialTheme {
        Scaffold(baseNavigator = rememberBaseNavigator(), state = state) {
            composable(PREVIEW_ROUTE_PATTERN) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        TextState(
                            text = "Content slot",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.displayMedium
                        )
                    )
                }
            }
        }
    }
}
