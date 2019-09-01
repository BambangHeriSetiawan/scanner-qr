package com.simx.qr.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import com.simx.qr.core.ViewFinderView

/**
 * Created by simx on 01,September,2019
 */
open class CustomViewFinderView constructor(context: Context): ViewFinderView(context) {
    private val pain = Paint()
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}