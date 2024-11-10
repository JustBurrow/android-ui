package kr.lul.android.ui.sample.viewmodel

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kr.lul.android.ui.sample.ui.navigator.FirstNavigator
import kr.lul.android.ui.sample.ui.navigator.SecondNavigator
import kr.lul.android.ui.scaffold.state.bottom.BottomNavigationItem
import kr.lul.android.ui.scaffold.state.bottom.NavigationBottomState
import kr.lul.android.ui.scaffold.state.fab.FabState
import kr.lul.android.ui.scaffold.state.top.IconTopState
import kr.lul.android.ui.scaffold.viewmodel.ScaffoldContentViewModel
import kr.lul.android.ui.state.BlockingProgressState
import kr.lul.android.ui.state.IconState
import kr.lul.android.ui.state.NonBlockingProgressState
import kr.lul.android.ui.state.TextState
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor() : ScaffoldContentViewModel("SecondViewModel") {
    fun onClickBlocking() {
        Log.d(tag, "#onClickBlocking called.")

        launch(BlockingProgressState) {
            delay(1000)
        }
    }

    fun onClickNonBlocking() {
        Log.d(tag, "#onClickNonBlocking called.")

        launch(NonBlockingProgressState) {
            delay(3000)
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)

        scaffoldPump.pump(
            top = IconTopState(IconState(drawable = android.R.drawable.ic_menu_help)),
            bottom = NavigationBottomState(
                items = listOf(
                    BottomNavigationItem(
                        icon = IconState(imageVector = Icons.Default.Home),
                        label = TextState("First"),
                        destination = FirstNavigator.Companion
                    ),
                    BottomNavigationItem(
                        icon = IconState(imageVector = Icons.AutoMirrored.Filled.ExitToApp),
                        label = TextState("Second"),
                        destination = SecondNavigator.Companion
                    )
                )
            ),
            fab = FabState.NONE
        )
    }

    override fun toString() = listOf(
        super.toString()
    ).joinToString(", ", "$tag(", ")")
}