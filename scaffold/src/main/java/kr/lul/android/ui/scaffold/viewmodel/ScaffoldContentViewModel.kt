package kr.lul.android.ui.scaffold.viewmodel

import androidx.lifecycle.LifecycleOwner
import kr.lul.android.ui.scaffold.pump.ScaffoldPump
import kr.lul.android.ui.scaffold.state.bottom.BottomState
import kr.lul.android.ui.scaffold.state.fab.FabPosition
import kr.lul.android.ui.scaffold.state.fab.FabState
import kr.lul.android.ui.scaffold.state.top.TopState
import kr.lul.android.ui.viewmodel.base.BaseViewModel
import kr.lul.android.ui.viewmodel.base.ProgressPump
import javax.inject.Inject

abstract class ScaffoldContentViewModel(
    tag: String
) : BaseViewModel(tag) {
    @Inject
    lateinit var scaffoldPump: ScaffoldPump

    final override val progress: ProgressPump
        get() = scaffoldPump

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)

        scaffoldPump.pump(
            top = TopState.NONE,
            bottom = BottomState.NONE,
            fab = FabState.NONE,
            fabPosition = FabPosition.END
        )
    }

    override fun toString() = listOf(
        super.toString(),
        "scaffoldPump=$scaffoldPump"
    ).joinToString(", ", "$tag(", ")")
}