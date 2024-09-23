package kr.lul.ui.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import kr.lul.navigation.compose.rememberBaseNavigator
import kr.lul.ui.ui.Root
import kr.lul.ui.ui.navigator.FirstNavigator

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "#onCreate args : savedInstanceState=$savedInstanceState")
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            Root(rememberBaseNavigator(this, FirstNavigator))
        }
    }
}