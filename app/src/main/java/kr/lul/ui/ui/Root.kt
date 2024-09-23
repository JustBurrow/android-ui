package kr.lul.ui.ui

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import kr.lul.navigation.compose.page
import kr.lul.navigation.compose.rememberBaseNavigator
import kr.lul.navigation.navigator.BaseNavigator
import kr.lul.ui.ui.navigator.FirstNavigator
import kr.lul.ui.ui.navigator.SecondNavigator
import kr.lul.ui.ui.page.FirstPage
import kr.lul.ui.ui.page.SecondPage

@Composable
fun Root(
    baseNavigator: BaseNavigator = rememberBaseNavigator()
) {
    Log.v("ui", "#Root args : baseNavigator=$baseNavigator")

    MaterialTheme {
        NavHost(baseNavigator.navController, baseNavigator.destination.routePattern) {
            page(FirstNavigator(baseNavigator)) { _, navigator ->
                FirstPage(navigator)
            }
            page(SecondNavigator(baseNavigator)) { _, navigator ->
                SecondPage(navigator)
            }
        }
    }
}