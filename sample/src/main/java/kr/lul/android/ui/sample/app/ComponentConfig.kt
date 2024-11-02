package kr.lul.android.ui.sample.app

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.lul.android.ui.scaffold.pump.ScaffoldPump
import kr.lul.android.ui.scaffold.pump.ScaffoldPumpImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ComponentConfig {
    companion object {
        private const val TAG = "ComponentConfig"
    }

    @Provides
    @Singleton
    fun provideScaffoldPump(): ScaffoldPump {
        val pump = ScaffoldPumpImpl()
        Log.i(TAG, "#provideScaffoldPump return : $pump")
        return pump
    }
}