package com.simx.qr.my_qr

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData

/**
 * Created by simx on 01,September,2019
 */
class MyQrFragmentVM:BaseObservable() {
    @Bindable var code = MutableLiveData<String>()
}