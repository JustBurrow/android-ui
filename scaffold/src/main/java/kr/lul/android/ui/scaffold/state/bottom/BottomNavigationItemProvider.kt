package kr.lul.android.ui.scaffold.state.bottom

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kr.lul.android.ui.navigation.navigator.Destination

class BottomNavigationItemProvider : PreviewParameterProvider<BottomNavigationItem> {
    override val values = sequenceOf(
        BottomNavigationItem(
            icon = null,
            label = null,
            destination = object : Destination {
                override val routePattern = "noArg"
                override val arguments: List<NamedNavArgument> = listOf()
                override val deepLinks: List<NavDeepLink> = listOf()
                override fun route(vararg args: Any) = routePattern
            }
        ),
        BottomNavigationItem(
            icon = null,
            label = null,
            destination = object : Destination {
                override val routePattern = "requiredArg/{arg}"
                override val arguments: List<NamedNavArgument> = listOf(
                    navArgument("arg") {
                        nullable = false
                        type = NavType.StringType
                    }
                )
                override val deepLinks: List<NavDeepLink> = listOf()
                override fun route(vararg args: Any) = if (1 == args.size && args[0] is String) {
                    "requiredArg/${args[0]}"
                } else {
                    throw IllegalArgumentException("Unexpected arguments: args=${args.contentToString()}")
                }
            }
        ),
        BottomNavigationItem(
            icon = null,
            label = null,
            destination = object : Destination {
                override val routePattern = "optionalArg?arg={arg}"
                override val arguments: List<NamedNavArgument> = listOf(
                    navArgument("arg") {
                        nullable = true
                        type = NavType.StringType
                    }
                )
                override val deepLinks: List<NavDeepLink> = listOf()
                override fun route(vararg args: Any) = when {
                    args.isEmpty() ->
                        "optionalArg"

                    1 == args.size && args[0] is String ->
                        "optionalArg?arg=${args[0]}"

                    else ->
                        throw IllegalArgumentException("Unexpected arguments: args=${args.contentToString()}")
                }
            }
        )
    )
}