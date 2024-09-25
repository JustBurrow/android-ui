package kr.lul.android.ui.navigation.navigator

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.navigation.NavHostController

/**
 * 각 페이지의 [Navigator] 구현체의 공통 기능을 제공한다.
 *
 * **⚠️ 주의 : Single Activity 구조에서 사용해야 함.**
 *
 * 사용 예)
 * ```kotlin
 * class ActualNavigator(
 *     private val base: BaseNavigator
 * ) : Navigator by base {
 *    // ...
 * }
 * ```
 */
class BaseNavigator(
    val activity: Activity,
    val navController: NavHostController,
    /**
     * 첫 화면(`startDestination`).
     *
     * @see androidx.navigation.compose.NavHost
     */
    override val destination: Destination
) : Navigator {
    companion object {
        private const val TAG = "BaseNavigator"
    }

    override fun back() {
        Log.d(TAG, "#back called.")

        navController.popBackStack()
    }

    override fun settings() {
        Log.d(TAG, "#settings called.")

        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.fromParts("package", activity.packageName, null)
        activity.startActivity(intent)
    }

    override fun web(uri: Uri) {
        Log.d(TAG, "#web args : uri=$uri")

        val intent = Intent(Intent.ACTION_VIEW, uri)
        activity.startActivity(intent)
    }

    override fun call(phoneNumber: Uri) {
        Log.d(TAG, "#call args : phoneNumber=$phoneNumber")

        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = phoneNumber
        activity.startActivity(intent)
    }

    override fun toString() = listOf(
        "activity=$activity",
        "navController=$navController",
        "destination=$destination"
    ).joinToString(", ", "$TAG(", ")")
}
