package kr.lul.android.ui.scaffold.compose.bottom

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import kr.lul.android.ui.compose.Icon
import kr.lul.android.ui.compose.Text
import kr.lul.android.ui.navigation.compose.rememberBaseNavigator
import kr.lul.android.ui.navigation.navigator.BaseNavigator
import kr.lul.android.ui.scaffold.compose.TAG
import kr.lul.android.ui.scaffold.state.bottom.NavigationBottomState
import kr.lul.android.ui.scaffold.state.bottom.NavigationBottomStateProvider
import kr.lul.android.ui.state.hasTestTag

/**
 * 하단에 위치한 내비게이션 바.
 *
 * 참고 :
 * - [Integration with the bottom nav bar](https://developer.android.com/develop/ui/compose/navigation#bottom-nav)
 *
 * @param baseNavigator 내비게이션을 처리할 [BaseNavigator].
 * @param state 내비게이션 바의 상태.
 * @param modifier [Modifier].
 * @param containerColor 내비게이션 바의 배경색.
 * @param contentColor 내비게이션 바의 콘텐츠 색.
 * @param tonalElevation 내비게이션 바의 토널 고도.
 * @param contentPadding 내비게이션 바의 콘텐츠 패딩.
 * @param windowInsets 내비게이션 바의 창 인셋.
 * @param scrollBehavior 내비게이션 바의 스크롤 동작.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NavigationBottomBar(
    baseNavigator: BaseNavigator,
    state: NavigationBottomState,
    modifier: Modifier = Modifier,
    containerColor: Color = BottomAppBarDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    tonalElevation: Dp = BottomAppBarDefaults.ContainerElevation,
    contentPadding: PaddingValues = BottomAppBarDefaults.ContentPadding,
    windowInsets: WindowInsets = BottomAppBarDefaults.windowInsets,
    scrollBehavior: BottomAppBarScrollBehavior? = null,
) {
    Log.v(
        TAG, listOf(
            "baseNavigator=$baseNavigator",
            "state=$state",
            "modifier=$modifier",
            "containerColor=$containerColor",
            "contentColor=$contentColor",
            "tonalElevation=$tonalElevation",
            "contentPadding=$contentPadding",
            "windowInsets=$windowInsets",
            "scrollBehavior=$scrollBehavior"
        ).joinToString(", ", "#NavigationBottomBar args : ")
    )

    val actualModifier = remember(modifier) {
        if (modifier.hasTestTag()) {
            modifier
        } else {
            modifier.testTag(state.testTag)
        }
    }

    BottomAppBar(
        modifier = actualModifier,
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        contentPadding = contentPadding,
        windowInsets = windowInsets,
        scrollBehavior = scrollBehavior
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (item in state.items) {
                IconButton(
                    onClick = {
                        Log.d(
                            TAG, listOf(
                                "item.destination=${item.destination}",
                                "item.arguments=${item.arguments.contentToString()}",
                                "findStartDestination()=${baseNavigator.navController.graph.findStartDestination()}"
                            ).joinToString(", ", "#NavigationBottomBar item clicked : ")
                        )
                        baseNavigator.navController.navigate(item.destination.route(*item.arguments)) {
                            popUpTo(baseNavigator.navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }, modifier = Modifier.weight(1f)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        item.icon?.let { Icon(it) }
                        Spacer(Modifier.weight(1f))
                        item.label?.let { Text(it) }
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
private fun PreviewNavigationBottomBar(@PreviewParameter(NavigationBottomStateProvider::class) state: NavigationBottomState) {
    MaterialTheme {
        NavigationBottomBar(rememberBaseNavigator(), state, Modifier.fillMaxWidth())
    }
}