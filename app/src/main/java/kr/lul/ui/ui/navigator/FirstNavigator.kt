package kr.lul.ui.ui.navigator

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import kr.lul.navigation.navigator.BaseNavigator
import kr.lul.navigation.navigator.Destination
import kr.lul.navigation.navigator.Navigator

@Immutable
class FirstNavigator(
    private val base: BaseNavigator
) : Navigator by base {
    companion object : Destination {
        private const val TAG = "FirstNavigator"

        override val routePattern = "first"

        override val arguments: List<NamedNavArgument> = listOf()

        override val deepLinks: List<NavDeepLink> = listOf()

        override fun route(vararg args: Any): String = if (args.isEmpty()) {
            route()
        } else {
            throw IllegalArgumentException("No arguments are allowed.")
        }

        fun route() = routePattern

        override fun toString() =
            "FirstDestination(routePattern='$routePattern', arguments=$arguments, deepLinks=$deepLinks)"
    }

    override val destination: Destination = FirstNavigator

    fun second() {
        Log.d(TAG, "#second called.")
        base.navController.navigate(SecondNavigator.route())
    }

    override fun toString() = listOf(
        "base=$base"
    ).joinToString(", ", "$TAG(", ")")
}