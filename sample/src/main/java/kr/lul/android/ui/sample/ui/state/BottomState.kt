package kr.lul.android.ui.sample.ui.state

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import kr.lul.android.ui.sample.ui.navigator.FirstNavigator
import kr.lul.android.ui.sample.ui.navigator.SecondNavigator
import kr.lul.android.ui.scaffold.state.bottom.BottomNavigationItem
import kr.lul.android.ui.scaffold.state.bottom.NavigationBottomState
import kr.lul.android.ui.state.IconState
import kr.lul.android.ui.state.TextState

val NavigationBottomState = NavigationBottomState(
    items = listOf(
        BottomNavigationItem(
            icon = IconState(imageVector = Icons.Default.Home),
            label = TextState(text = "First"),
            destination = FirstNavigator.Companion
        ),
        BottomNavigationItem(
            icon = IconState(imageVector = Icons.AutoMirrored.Filled.ExitToApp),
            label = TextState(text = "Second"),
            destination = SecondNavigator.Companion
        )
    )
)