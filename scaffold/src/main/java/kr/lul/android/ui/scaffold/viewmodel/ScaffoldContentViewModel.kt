package kr.lul.android.ui.scaffold.viewmodel

import kr.lul.android.ui.scaffold.pump.ScaffoldPump
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

    override fun toString() = listOf(
        super.toString(),
        "scaffoldPump=$scaffoldPump"
    ).joinToString(", ", "$tag(", ")")
}