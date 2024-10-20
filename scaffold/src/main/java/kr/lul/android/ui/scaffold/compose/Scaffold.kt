package kr.lul.android.ui.scaffold.compose

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import kr.lul.android.ui.navigation.navigator.BaseNavigator

private const val TAG = "ui.scaffold"

/**
 * [androidx.compose.material3.Scaffold]를 확장해서 기본적인 기능을 제공한다.
 *
 * 1. 내비게이션 관리.
 *
 * @see androidx.compose.material3.Scaffold
 */
@Composable
fun Scaffold(
    baseNavigator: BaseNavigator,
    modifier: Modifier = Modifier,
    builder: NavGraphBuilder.() -> Unit
) {
    Log.v(
        TAG,
        listOf(
            "baseNavigator=$baseNavigator",
            "modifier=$modifier",
            "builder=$builder"
        ).joinToString(", ", "#Scaffold args : ")
    )

    androidx.compose.material3.Scaffold(modifier) {
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