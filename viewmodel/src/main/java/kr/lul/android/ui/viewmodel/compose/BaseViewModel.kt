package kr.lul.android.ui.viewmodel.compose

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import kr.lul.android.ui.viewmodel.base.BaseViewModel

/**
 * [BaseViewModel]기반 VM을 생성하고 반환한다.
 *
 * @param viewModelStoreOwner
 * @param key
 *
 * @see BaseViewModel
 */
@Composable
inline fun <reified VM : BaseViewModel> baseViewModel(
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    key: String? = null
): VM {
    Log.v(TAG, "#BaseViewModel args : viewModelStoreOwner=$viewModelStoreOwner, key=$key")

    val viewModel: VM = hiltViewModel(viewModelStoreOwner, key)
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(key, viewModel) {
        lifecycle.addObserver(viewModel)
        onDispose {
            lifecycle.removeObserver(viewModel)
        }
    }

    Log.v(TAG, "#BaseViewModel return : $viewModel")
    return viewModel
}