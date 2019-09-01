package com.simx.scanner

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

/**
 * Created by simx on 01,September,2019
 */
class Apps: Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}