package kr.lul.android.ui.scaffold.compose.bottom

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import kr.lul.android.ui.compose.Icon
import kr.lul.android.ui.scaffold.compose.TAG
import kr.lul.android.ui.scaffold.state.bottom.NavigationBottomState
import kr.lul.android.ui.state.hasTestTag

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NavigationBottomBar(
    state: NavigationBottomState,
    modifier: Modifier = Modifier,
    containerColor: Color = BottomAppBarDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    tonalElevation: Dp = BottomAppBarDefaults.ContainerElevation,
    contentPadding: PaddingValues = BottomAppBarDefaults.ContentPadding,
    windowInsets: WindowInsets = BottomAppBarDefaults.windowInsets,
    scrollBehavior: BottomAppBarScrollBehavior? = null,
) {
    Log.v(
        TAG, listOf(
            "state=$state",
            "modifier=$modifier",
            "containerColor=$containerColor",
            "contentColor=$contentColor",
            "tonalElevation=$tonalElevation",
            "contentPadding=$contentPadding",
            "windowInsets=$windowInsets",
            "scrollBehavior=$scrollBehavior"
        ).joinToString(", ", "#NavigationBottomBar args : ")
    )

    val actualModifier = if (modifier.hasTestTag()) {
        modifier
    } else {
        modifier.testTag(state.testTag)
    }

    BottomAppBar(
        actualModifier,
        containerColor,
        contentColor,
        tonalElevation,
        contentPadding,
        windowInsets,
        scrollBehavior
    ) {
        for (item in state.items) {
            IconButton(onClick = {}) {
                item.icon?.let { Icon(it) }
            }
        }
    }
}