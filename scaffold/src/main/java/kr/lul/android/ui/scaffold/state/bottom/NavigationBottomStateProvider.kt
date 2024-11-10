package kr.lul.android.ui.scaffold.state.bottom

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class NavigationBottomStateProvider : PreviewParameterProvider<NavigationBottomState> {
    override val values = sequenceOf(
        NavigationBottomState(
            items = BottomNavigationItemProvider().values.toList()
        )
    )
}