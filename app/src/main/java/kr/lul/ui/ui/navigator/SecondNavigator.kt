package kr.lul.ui.ui.navigator

import androidx.compose.runtime.Immutable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import kr.lul.navigation.navigator.BaseNavigator
import kr.lul.navigation.navigator.Destination
import kr.lul.navigation.navigator.Navigator

@Immutable
class SecondNavigator(
    private val base: BaseNavigator
) : Navigator by base {
    companion object : Destination {
        private const val TAG = "SecondNavigator"

        override val routePattern = "second"

        override val arguments: List<NamedNavArgument> = emptyList()

        override val deepLinks: List<NavDeepLink> = emptyList()

        override fun route(vararg args: Any): String = if (args.isEmpty()) {
            route()
        } else {
            throw IllegalArgumentException("No arguments are allowed.")
        }

        fun route() = routePattern

        override fun toString() =
            "SecondDestination(routePattern='$routePattern', arguments=$arguments, deepLinks=$deepLinks)"
    }

    override val destination: Destination = SecondNavigator

    override fun toString() = listOf(
        "base=$base"
    ).joinToString(", ", "$TAG(", ")")
}
