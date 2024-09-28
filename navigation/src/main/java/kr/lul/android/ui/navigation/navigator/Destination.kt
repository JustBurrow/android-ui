package kr.lul.android.ui.navigation.navigator

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

/**
 * 네비게이션 그래프 상의 목적지(`@Composable` 함수).
 *
 * @see androidx.navigation.compose.composable
 */
interface Destination {
    /**
     * 목적지의 경로 패턴. 목적지 식별자.
     */
    val routePattern: String

    /**
     * [routePattern]에 바인딩 해야 하는 인자.
     */
    val arguments: List<NamedNavArgument>

    /**
     * 목적지에 대한 딥 링크.
     */
    val deepLinks: List<NavDeepLink>

    /**
     * 목적지의 경로를 생성한다.
     *
     * @see androidx.navigation.NavController.navigate
     */
    fun route(vararg args: Any): String
}