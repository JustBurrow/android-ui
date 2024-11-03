package kr.lul.android.ui.sample.app

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleApplication : Application() {
    companion object {
        private const val TAG = "SampleApplication"
    }

    override fun onCreate() {
        Log.i(TAG, "#onCreate called.")
        super.onCreate()
    }
}