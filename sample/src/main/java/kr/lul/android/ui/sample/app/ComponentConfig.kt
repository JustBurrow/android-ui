package kr.lul.android.ui.sample.app

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.lul.android.ui.viewmodel.base.PriorityProgressViewModelet
import kr.lul.android.ui.viewmodel.base.ProgressViewModelet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ComponentConfig {
    companion object {
        private const val TAG = "ComponentConfig"
    }

    @Provides
    @Singleton
    fun provideProgressViewModelet(): ProgressViewModelet {
        val vm = PriorityProgressViewModelet()
        Log.i(TAG, "#provideProgressViewModelet return : $vm")
        return vm
    }
}