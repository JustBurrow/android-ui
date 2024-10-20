package kr.lul.android.ui.sample.app

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.lul.android.ui.viewmodel.base.PriorityProgressPump
import kr.lul.android.ui.viewmodel.base.ProgressPump
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ComponentConfig {
    companion object {
        private const val TAG = "ComponentConfig"
    }

    @Provides
    @Singleton
    fun provideProgressPump(): ProgressPump {
        val pump = PriorityProgressPump()
        Log.i(TAG, "#provideProgressPump return : $pump")
        return pump
    }
}