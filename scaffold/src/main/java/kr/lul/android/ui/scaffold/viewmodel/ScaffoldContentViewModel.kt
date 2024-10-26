package kr.lul.android.ui.scaffold.viewmodel

import kr.lul.android.ui.viewmodel.base.BaseViewModel
import kr.lul.android.ui.viewmodel.base.ProgressPump
import javax.inject.Inject

abstract class ScaffoldContentViewModel(
    tag: String
) : BaseViewModel(tag) {
    @Inject
    override lateinit var progress: ProgressPump

    override fun toString() = listOf(
        super.toString()
    ).joinToString(", ", "$tag(", ")")
}