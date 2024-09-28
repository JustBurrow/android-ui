package kr.lul.android.ui.navigation.compose

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.lul.android.ui.navigation.navigator.Navigator

/**
 * 어떤 화면에서 사용할 수 있는 화면 이동([Navigator])에서 그 화면이 어떤 화면([Destination])인지 알아내서 내비게이션 그래프를 구성한다.
 *
 * @see androidx.navigation.compose.composable
 */
inline fun <N : Navigator> NavGraphBuilder.page(
    navigator: N,
    noinline enterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    noinline exitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    noinline popEnterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    noinline popExitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
    noinline sizeTransform: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> SizeTransform?)? = null,
    crossinline content: @Composable AnimatedContentScope.(NavBackStackEntry, N) -> Unit
) {
    composable(
        route = navigator.destination.routePattern,
        arguments = navigator.destination.arguments,
        deepLinks = navigator.destination.deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        sizeTransform = sizeTransform
    ) {
        content(it, navigator)
    }
}