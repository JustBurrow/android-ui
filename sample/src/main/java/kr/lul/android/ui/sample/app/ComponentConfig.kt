package kr.lul.android.ui.sample.app

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ComponentConfig {
    companion object {
        private const val TAG = "ComponentConfig"
    }
}