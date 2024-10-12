package kr.lul.android.ui.sample.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.lul.android.ui.viewmodel.base.BaseViewModel
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor() : BaseViewModel("FirstViewModel") {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        Log.i(tag, "#onCreate : this=$this")
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun toString() = listOf(
        "hashCode=${hashCode().toHexString(HexFormat.Default).uppercase(Locale.getDefault())}",
    ).joinToString(", ", "$tag(", ")")
}