package kr.lul.android.ui.sample.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.lul.android.ui.viewmodel.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor() : BaseViewModel("SecondViewModel")
