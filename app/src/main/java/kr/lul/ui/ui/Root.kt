package kr.lul.ui.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kr.lul.navigation.navigator.BaseNavigator
import kr.lul.ui.ui.navigator.FirstNavigator
import kr.lul.ui.ui.navigator.SecondNavigator
import kr.lul.ui.ui.page.FirstPage
import kr.lul.ui.ui.page.SecondPage

@Composable
fun Root(baseNavigator: BaseNavigator) {
    Log.v("ui", "#Root args : baseNavigator=$baseNavigator")

    NavHost(baseNavigator.navController, baseNavigator.destination.routePattern) {
        composable(FirstNavigator.routePattern, FirstNavigator.arguments, FirstNavigator.deepLinks) {
            FirstPage(FirstNavigator(baseNavigator))
        }

        composable(SecondNavigator.routePattern, SecondNavigator.arguments, SecondNavigator.deepLinks) {
            SecondPage(SecondNavigator(baseNavigator))
        }
    }
}