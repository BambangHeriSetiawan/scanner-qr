package com.simx.qr.tools

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder

/**
 * Created by simx on 16,September,2019
 */
object QrTools {
    @Throws(WriterException::class)
    fun generateQr(value : String?) :Bitmap? {
        var bitMatrix: BitMatrix? = null
        var multiFormatWriter = MultiFormatWriter()
        var barcodeEncoder = BarcodeEncoder()
        try {
            bitMatrix = multiFormatWriter.encode(value, BarcodeFormat.QR_CODE,500,500)
        }catch (e: Exception){
            e.printStackTrace()
        }
        return barcodeEncoder.createBitmap(bitMatrix)
    }
    @Throws(WriterException::class)
    fun generateQr(value : Int?) :Bitmap? {
        var bitMatrix: BitMatrix? = null
        var multiFormatWriter = MultiFormatWriter()
        var barcodeEncoder = BarcodeEncoder()
        try {
            bitMatrix = multiFormatWriter.encode(value.toString(), BarcodeFormat.QR_CODE,500,500)
        }catch (e: Exception){
            e.printStackTrace()
        }
        return barcodeEncoder.createBitmap(bitMatrix)
    }
}