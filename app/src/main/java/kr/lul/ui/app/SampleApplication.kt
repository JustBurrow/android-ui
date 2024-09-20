package kr.lul.ui.app

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class SampleApplication : Application() {
    companion object {
        private const val TAG = "SampleApplication"
    }

    private val scope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        Log.i(TAG, "#onCreate called.")
        super.onCreate()
    }
}